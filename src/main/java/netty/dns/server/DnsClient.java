package netty.dns.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.dns.DatagramDnsQuery;
import io.netty.handler.codec.dns.DatagramDnsQueryEncoder;
import io.netty.handler.codec.dns.DatagramDnsResponse;
import io.netty.handler.codec.dns.DatagramDnsResponseDecoder;
import io.netty.handler.codec.dns.DefaultDnsQuestion;
import io.netty.handler.codec.dns.DnsQuery;
import io.netty.handler.codec.dns.DnsQuestion;
import io.netty.handler.codec.dns.DnsRawRecord;
import io.netty.handler.codec.dns.DnsRecord;
import io.netty.handler.codec.dns.DnsRecordType;
import io.netty.handler.codec.dns.DnsSection;
import netty.dns.DnsConfig;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * 从netty的demo里抄的
 */
public class DnsClient {
    private DnsConfig dnsConfig= BeanContext.getApplicationContext().getBean(DnsConfig.class);

    private byte[] ip;

    private void handleQueryResp(DatagramDnsResponse msg) {
        if (msg.count(DnsSection.QUESTION) > 0) {
            DnsQuestion question = msg.recordAt(DnsSection.QUESTION, 0);
        }
        for (int i = 0, count = msg.count(DnsSection.ANSWER); i < count; i++) {
            DnsRecord record = msg.recordAt(DnsSection.ANSWER, i);
            if (record.type() == DnsRecordType.A) {
                DnsRawRecord raw = (DnsRawRecord) record;
                ip = ByteBufUtil.getBytes(raw.content());
                //只取第一个记录返回
                break;
            }

        }
    }

    public byte[] query(String domain) {
        InetSocketAddress addr = new InetSocketAddress(dnsConfig.getRemoteDnsHost(), dnsConfig.getRemoteDnsPort());
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .handler(new ChannelInitializer<DatagramChannel>() {
                        @Override
                        protected void initChannel(DatagramChannel ch)  {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new DatagramDnsQueryEncoder())
                                    .addLast(new DatagramDnsResponseDecoder())
                                    .addLast(new SimpleChannelInboundHandler<DatagramDnsResponse>() {
                                        @Override
                                        protected void channelRead0(ChannelHandlerContext ctx, DatagramDnsResponse msg) {
                                            try {
                                                handleQueryResp(msg);
                                            } finally {
                                                ctx.close();
                                            }
                                        }
                                    });
                        }
                    });
            final Channel ch = b.bind(0).sync().channel();
            DnsQuery query = new DatagramDnsQuery(null, addr, 1).setRecord(
                    DnsSection.QUESTION,
                    new DefaultDnsQuestion(domain, DnsRecordType.A));
            ch.writeAndFlush(query).sync();
            boolean success = ch.closeFuture().await(10, TimeUnit.SECONDS);

            if (!success) {
                System.err.println("dns query timeout!");
                ch.close().sync();
            }
            group.shutdownGracefully();

            return (ip == null) ? new byte[]{0, 0, 0, 0} : ip;
            //如果上游dns有记录则返回记录,没记录则返回127.0.0.1
            //return ip;
        } catch (InterruptedException e) {
            group.shutdownGracefully();
            return null;
        }
    }
}
