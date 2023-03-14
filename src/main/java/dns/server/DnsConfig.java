package dns.server;

/**
 * 全局配置方法,从config.properties中读取
 */
public class DnsConfig {

    public final static String localhost_dns_host = "192.168.10.200";
    public final static int localhost_dns_port = 53;
    public final static String remote_dns_host = "223.5.5.5";
    public final static int remote_dns_port = 53;
    public final static byte[] default_ip = new byte[]{127, 0, 0, 1};
    public final static int default_ttl = 3600;

}
