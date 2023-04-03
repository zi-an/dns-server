package netty.dns.dao;

public class LogEntity {

    private long id;
    private String time;
    private String type;
    private String client;
    private String name;
    private String ip;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public LogEntity(String time, String type, String client, String name, String ip) {
        this.time = time;
        this.type = type;
        this.client = client;
        this.name = name;
        this.ip = ip;
    }
}
