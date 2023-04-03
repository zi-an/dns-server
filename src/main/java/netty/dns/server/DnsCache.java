package netty.dns.server;

import netty.dns.DnsConfig;
import netty.dns.dao.LogMapper;
import netty.dns.dao.Record;
import netty.dns.dao.RecordMapper;
import netty.dns.util.Util;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public class DnsCache {
    private static final Map<String, byte[]> domainIpMapping = new HashMap<>();

    @Resource
    static RecordMapper recordsMapper;

    DnsConfig dnsConfig = BeanContext.getApplicationContext().getBean(DnsConfig.class);

    public DnsCache() {
        loadRecords();
    }

    public static Map<String, byte[]> getDomainIpMapping() {
        return domainIpMapping;
    }

    public static void loadRecords() {
        recordsMapper=BeanContext.getApplicationContext().getBean(RecordMapper.class);
        for (Record record : recordsMapper.selectAll()) {
            domainIpMapping.put(record.getDomain(), Util.ip2byte(record.getIp()));
        }
        System.out.println("ok");
    }

}
