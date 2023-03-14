package dns.server;

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

import java.util.HashMap;
import java.util.Map;

public class DnsHandler extends SimpleChannelInboundHandler<DatagramDnsQuery> {
    private final Map<String, byte[]> domainIpMapping = new HashMap<>();

    public DnsHandler() {
        // 本地加一些临时数据，正确做法是入库
        domainIpMapping.put("1.mm.", new byte[]{(byte) 192, (byte) 168, (byte) 10, 1});
        domainIpMapping.put("5.mm.", new byte[]{(byte) 192, (byte) 168, (byte) 10, 5});
        domainIpMapping.put("200.mm.", new byte[]{(byte) 192, (byte) 168, (byte) 10, (byte) 200});
        domainIpMapping.put("201.mm.", new byte[]{(byte) 192, (byte) 168, (byte) 10, (byte) 201});
        domainIpMapping.put("202.mm.", new byte[]{(byte) 192, (byte) 168, (byte) 10, (byte) 202});
        domainIpMapping.put("203.mm.", new byte[]{(byte) 192, (byte) 168, (byte) 10, (byte) 23});
        domainIpMapping.put("204.mm.", new byte[]{(byte) 192, (byte) 168, (byte) 10, (byte) 204});
        domainIpMapping.put("205.mm.", new byte[]{(byte) 192, (byte) 168, (byte) 10, (byte) 205});
        domainIpMapping.put("206.mm.", new byte[]{(byte) 192, (byte) 168, (byte) 10, (byte) 206});
        domainIpMapping.put("207.mm.", new byte[]{(byte) 192, (byte) 168, (byte) 10, (byte) 207});
        domainIpMapping.put("208.mm.", new byte[]{(byte) 192, (byte) 168, (byte) 10, (byte) 208});
        domainIpMapping.put("209.mm.", new byte[]{(byte) 192, (byte) 168, (byte) 10, (byte) 209});
        domainIpMapping.put("210.mm.", new byte[]{(byte) 192, (byte) 168, (byte) 10, (byte) 210});
        domainIpMapping.put("ws.p2p.huya.com.", DnsConfig.default_ip);
        domainIpMapping.put("update.miui.com.", DnsConfig.default_ip);
        domainIpMapping.put("arm.lczzjj.cn.", DnsConfig.default_ip);
        //酷我音乐,耗子版需要屏蔽
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, DatagramDnsQuery datagramDnsQuery) {
        DatagramDnsResponse datagramDnsResponse = new DatagramDnsResponse(datagramDnsQuery.recipient(), datagramDnsQuery.sender(), datagramDnsQuery.id());
        try {
            DefaultDnsQuestion dnsQuestion = datagramDnsQuery.recordAt(DnsSection.QUESTION);
            datagramDnsResponse.addRecord(DnsSection.QUESTION, dnsQuestion);

            ByteBuf byteBuf;
            String domain = dnsQuestion.name();
            String logs = System.currentTimeMillis() + "," + datagramDnsQuery.sender().getAddress().getHostAddress() + ",";
            //日志,等数据准备完毕再打印,减小消耗
            //System.out.print(System.currentTimeMillis()+","+datagramDnsQuery.sender().getAddress().getHostAddress()+",");
            //打印客户端ip
            if (domainIpMapping.containsKey(dnsQuestion.name())) {
                //如果在ipMapping表中的,返回结果
                byteBuf = Unpooled.wrappedBuffer(domainIpMapping.get(dnsQuestion.name()));
                //System.out.printf("cache,%s,%s%n", domain, NetUtil.bytesToIpAddress(domainIpMapping.get(domain)));
                logs += "cache," + domain + "," + NetUtil.bytesToIpAddress(domainIpMapping.get(domain));
            } else {
                // 不在 ipMapping表中的域名,从上游dns获取后加入到ipMapping表中
                byte[] result = new DnsClient().query(domain.substring(0, domain.length() - 1));
                byteBuf = Unpooled.wrappedBuffer(result);
                if (result[0] != 0) {
                    domainIpMapping.put(dnsQuestion.name(), result);
                    //System.out.printf("storage,%s,%s%n", domain, NetUtil.bytesToIpAddress(domainIpMapping.get(domain)));
                }
                logs += "storage," + domain + "," + NetUtil.bytesToIpAddress(result);
            }
            System.out.println(logs);
            DefaultDnsRawRecord queryAnswer = new DefaultDnsRawRecord(dnsQuestion.name(),
                    DnsRecordType.A, DnsConfig.default_ttl, byteBuf);
            datagramDnsResponse.addRecord(DnsSection.ANSWER, queryAnswer);

        } catch (Exception e) {
            System.out.println("exception:" + e);
        } finally {
            ctx.writeAndFlush(datagramDnsResponse);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
    }
}