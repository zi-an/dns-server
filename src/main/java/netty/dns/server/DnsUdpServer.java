package netty.dns.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.dns.DatagramDnsQueryDecoder;
import io.netty.handler.codec.dns.DatagramDnsResponseEncoder;
import netty.dns.DnsConfig;
import netty.dns.util.BeanContext;

public class DnsUdpServer {
    DnsConfig dnsConfig=  BeanContext.getApplicationContext().getBean(DnsConfig.class);
    public static void main(String[] args) {
        DnsUdpServer dnsServer = new DnsUdpServer();
        dnsServer.listenAndRun();
    }

    public void listenAndRun() {
        final NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioDatagramChannel.class)
                    .handler(new ChannelInitializer<NioDatagramChannel>() {
                        @Override
                        protected void initChannel(NioDatagramChannel nioDatagramChannel) {
                            nioDatagramChannel.pipeline().addLast(new DatagramDnsQueryDecoder());
                            nioDatagramChannel.pipeline().addLast(new DatagramDnsResponseEncoder());
                            nioDatagramChannel.pipeline().addLast(new DnsHandler());
                        }
                    }).option(ChannelOption.SO_BROADCAST, true);

            ChannelFuture future = bootstrap.bind(dnsConfig.getLocalhostDnsHost(), dnsConfig.getLocalhostDnsPort()).sync();
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            System.err.println("配置错误:请检查DnsConfig.java");
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
