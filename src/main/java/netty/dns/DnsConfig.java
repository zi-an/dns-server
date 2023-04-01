package netty.dns;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("netty.dns")
@Component
public class DnsConfig {
    /**
     * 绑定的ip地址
     */
    private String localhostDnsHost;
    /**
     * 默认本地端口53
     */
    private int localhostDnsPort = 53;
    /**
     * 远程地址
     */
    private String remoteDnsHost = "223.5.5.5";
    /**
     * 远程端口
     */
    private int remoteDnsPort = 53;
    /**
     * 默认找不到的ip
     */
    private byte[] defaultIp = new byte[]{127, 0, 0, 1};
    /**
     * 默认有效时间
     */
    private int defaultTtl = 3600;

    public String getLocalhostDnsHost() {
        return localhostDnsHost;
    }

    public int getLocalhostDnsPort() {
        return localhostDnsPort;
    }

    public void setLocalhostDnsPort(int localhostDnsPort) {
        this.localhostDnsPort = localhostDnsPort;
    }

    public String getRemoteDnsHost() {
        return remoteDnsHost;
    }

    public void setRemoteDnsHost(String remoteDnsHost) {
        this.remoteDnsHost = remoteDnsHost;
    }

    public int getRemoteDnsPort() {
        return remoteDnsPort;
    }

    public void setRemoteDnsPort(int remoteDnsPort) {
        this.remoteDnsPort = remoteDnsPort;
    }

    public byte[] getDefaultIp() {
        return defaultIp;
    }

    public void setDefaultIp(byte[] defaultIp) {
        this.defaultIp = defaultIp;
    }

    public int getDefaultTtl() {
        return defaultTtl;
    }

    public void setDefaultTtl(int defaultTtl) {
        this.defaultTtl = defaultTtl;
    }


    {
        //windows 10的系统服务会把127.0.0.1的53端口绑定,导致异常
        if (System.getProperties().getProperty("os.name").toUpperCase().contains("WINDOWS")) {
            //return "0.0.0.0";
            localhostDnsHost = "192.168.10.7";
        } else {
            localhostDnsHost = "0.0.0.0";
        }
    }
}
