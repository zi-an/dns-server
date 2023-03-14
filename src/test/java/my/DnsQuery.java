package my;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class DnsQuery {


    static byte[] getIpByDomain(String domain) {
        byte[] bytes ;
        try {
            //System.setProperty("sun.net.spi.nameservice.nameservers",
            //"223.5.5.5");
            //System.setProperty("sun.net.spi.nameservice.provider.1", "dns,sun");


            InetAddress inetAddress = InetAddress.getByName(domain);
            System.out.println(inetAddress.getHostAddress());
            bytes = Util.ip2byte(inetAddress.getHostAddress());
            return bytes;
        } catch (UnknownHostException e) {
            return Util.ip2byte("127.0.0.1");
        }
    }

    public static void main(String[] args) throws Exception {
        //JDK9以上用hosts文件
        //https://bugs.java.com/bugdatabase/view_bug.do?bug_id=JDK-8134577
        System.setProperty("jdk.net.hosts.file", "D:/hosts");
        for (int i = 0; i < 5000; i++) {
            System.out.print(i + ":");
            getIpByDomain("update.miui.com");

            Thread.sleep(1000);
        }
    }
}
