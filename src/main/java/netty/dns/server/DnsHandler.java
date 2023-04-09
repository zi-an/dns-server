package netty.dns.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.dns.DatagramDnsQuery;
import io.netty.handler.codec.dns.DatagramDnsResponse;
import io.netty.handler.codec.dns.DefaultDnsQuestion;
import io.netty.handler.codec.dns.DefaultDnsRawRecord;
import io.netty.handler.codec.dns.DnsRecordType;
import io.netty.handler.codec.dns.DnsSection;
import io.netty.util.NetUtil;
import netty.dns.DnsConfig;
import netty.dns.dao.LogEntity;
import netty.dns.dao.LogMapper;
import netty.dns.util.BeanContext;
import netty.dns.util.Util;

public class DnsHandler extends SimpleChannelInboundHandler<DatagramDnsQuery> {

    private final LogMapper logMapper;

    DnsConfig dnsConfig = BeanContext.getApplicationContext().getBean(DnsConfig.class);

    public DnsHandler() {
        DnsCache.loadRecords();
        logMapper = BeanContext.getApplicationContext().getBean(LogMapper.class);
    }

    public void channelRead0(ChannelHandlerContext ctx, DatagramDnsQuery datagramDnsQuery) {
        DatagramDnsResponse datagramDnsResponse = new DatagramDnsResponse(datagramDnsQuery.recipient(), datagramDnsQuery.sender(), datagramDnsQuery.id());
        String domain = null;
        try {
            DefaultDnsQuestion dnsQuestion = datagramDnsQuery.recordAt(DnsSection.QUESTION);
            datagramDnsResponse.addRecord(DnsSection.QUESTION, dnsQuestion);

            ByteBuf byteBuf;
            //DefaultDnsRawRecord里域名是带顶级的.
            //所以带.的用dnsQuestion.name(),日常用的不带.的用domain
            domain = dnsQuestion.name();
            domain = domain.substring(0, domain.length() - 1);
            if (DnsCache.getDomainIpMapping().containsKey(domain)) {
                //如果在ipMapping表中的,返回结果
                byteBuf = Unpooled.wrappedBuffer(DnsCache.getDomainIpMapping().get(domain));
            } else if (domain.indexOf("in-addr.arpa") > 0) {
                //PTR记录,暂时默认为127.0.0.1,以后修复
                DnsCache.getDomainIpMapping().put(domain, Util.ip2byte("127.0.0.1"));
                byteBuf = Unpooled.wrappedBuffer(DnsCache.getDomainIpMapping().get(domain));
            } else {
                // 不在 ipMapping表中的域名,从上游dns获取后加入到ipMapping表中
                byte[] result = new DnsClient().query(domain);
                byteBuf = Unpooled.wrappedBuffer(result);
                if (result[0] != 0) {
                    DnsCache.getDomainIpMapping().put(domain, result);
                }
            }
            DefaultDnsRawRecord queryAnswer = new DefaultDnsRawRecord(dnsQuestion.name(),
                    DnsRecordType.A, dnsConfig.getDefaultTtl(), byteBuf);
            datagramDnsResponse.addRecord(DnsSection.ANSWER, queryAnswer);
            LogEntity logsEntity = new LogEntity(String.valueOf(System.currentTimeMillis() / 1000),
                    "1",
                    datagramDnsQuery.sender().getAddress().getHostAddress(),
                    domain,
                    NetUtil.bytesToIpAddress(DnsCache.getDomainIpMapping().get(domain)));
            logMapper.insertOne(logsEntity);
        } catch (Exception e) {
            System.out.println(Util.getNow() + ":" + domain + "exception:" + e);
        } finally {
            ctx.writeAndFlush(datagramDnsResponse);
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
    }
}