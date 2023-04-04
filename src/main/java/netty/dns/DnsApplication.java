package netty.dns;

import netty.dns.server.DnsUdpServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DnsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DnsApplication.class, args);
        //程序pid
        //System.out.println("pid:"+ProcessHandle.current().pid());
        //启动DNS
        DnsUdpServer dnsServer = new DnsUdpServer();
        dnsServer.listenAndRun();

    }

}
