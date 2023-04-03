package netty.dns.dao;


public class Records {

    private long id;
    private String domain;
    private String ip;

    public Records(long id, String domain, String ip) {
        this.id = id;
        this.domain = domain;
        this.ip = ip;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
