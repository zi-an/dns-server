package netty.dns.server;

import netty.dns.DnsConfig;
import netty.dns.dao.RecordEntity;
import netty.dns.dao.RecordMapper;
import netty.dns.util.BeanContext;
import netty.dns.util.Util;

import java.util.HashMap;
import java.util.Map;

public class DnsCache {
    private static final Map<String, byte[]> domainIpMapping = new HashMap<>();

    DnsConfig dnsConfig = BeanContext.getApplicationContext().getBean(DnsConfig.class);

    public DnsCache() {
        loadRecords();
    }

    public static Map<String, byte[]> getDomainIpMapping() {
        return domainIpMapping;
    }

    public static void loadRecords() {
        RecordMapper recordsMapper = BeanContext.getApplicationContext().getBean(RecordMapper.class);
        domainIpMapping.clear();
        for (RecordEntity record : recordsMapper.selectAll()) {
            domainIpMapping.put(record.getDomain(), Util.ip2byte(record.getIp()));
        }
        System.out.println(Util.getNow()+":DnsCache had reloaded");
    }

}
