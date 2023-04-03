package netty.dns;

import netty.dns.server.UdpDnsServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class DnsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DnsApplication.class, args);
        //程序pid
        //System.out.println("pid:"+ProcessHandle.current().pid());
        //启动DNS
        UdpDnsServer dnsServer = new UdpDnsServer();
        dnsServer.listenAndRun();

    }

}
