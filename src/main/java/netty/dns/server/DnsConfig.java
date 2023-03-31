package netty.dns.server;

/**
 * 全局配置方法,从config.properties中读取
 */
public class DnsConfig {


    //public static String localhost_dns_host = "192.168.10.7";
    public static String localhost_dns_host =isWindow();
    public final static int localhost_dns_port = 53;
    public final static String remote_dns_host = "223.5.5.5";
    public final static int remote_dns_port = 53;
    public final static byte[] default_ip = new byte[]{127, 0, 0, 1};
    public final static int default_ttl = 3600;

    static String isWindow() {
        //windows 10的系统服务会把127.0.0.1的53端口绑定,导致异常
        if (System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS") >=0) {
            //return "0.0.0.0";
            return "192.168.10.7";
        } else {
            return "0.0.0.0";
        }
    }
}
