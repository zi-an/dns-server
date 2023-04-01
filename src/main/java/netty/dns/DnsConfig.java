package netty.dns;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

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

    {
        //windows 10的系统服务会把127.0.0.1的53端口绑定,导致异常,这里自动获取192段地址
        if (System.getProperties().getProperty("os.name").toUpperCase().contains("WINDOWS")) {
            {
                // 获得本机的所有网络接口
                Enumeration<NetworkInterface> networkInterfaces;
                try {
                    networkInterfaces = NetworkInterface.getNetworkInterfaces();

                    //找到192的标志,找到跳出所有循环
                    boolean flags = false;
                    //遍历ip,找到192段的ip
                    while (networkInterfaces.hasMoreElements()) {
                        NetworkInterface networkInterface = networkInterfaces.nextElement();
                        Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                        if (flags) break;
                        while (addresses.hasMoreElements()) {
                            InetAddress addr = addresses.nextElement();
                            // 只关心 IPv4 地址
                            if (addr instanceof Inet4Address && addr.getHostAddress().contains("192.")) {
                                localhostDnsHost = addr.getHostAddress();
                                flags = true;
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else {
            localhostDnsHost = "0.0.0.0";
        }
    }

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
}
