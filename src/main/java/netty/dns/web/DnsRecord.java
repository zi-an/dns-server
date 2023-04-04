package netty.dns.web;

import netty.dns.dao.RecordEntity;
import netty.dns.dao.RecordMapper;
import netty.dns.server.DnsCache;
import netty.dns.util.Util;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/record")
public class DnsRecord {
    @Resource
    RecordMapper recordMapper;

    @RequestMapping("/getAll")
    List<RecordEntity> selectAll() {
        return recordMapper.selectAll();
    }

    @RequestMapping("/getData")
    List<RecordEntity> selectData() {
        return recordMapper.selectData();
    }

    @RequestMapping("/insert")
    long insertOne(@RequestParam(name = "domain") String domain,
                   @RequestParam(name = "ip") String ip) {
        if (DnsCache.getDomainIpMapping().containsKey(domain)) {
            return -1;
        } else if (!Util.isIPv4(ip)) {
            return 0;
        } else {
            System.out.println(domain + ip);
            RecordEntity recordEntity = new RecordEntity(domain, ip);
            recordMapper.insertOne(recordEntity);
            DnsCache.loadRecords();
            return recordEntity.getId();
        }
    }

    @RequestMapping("/delete")
    int deleteOne(@RequestParam(name = "id") int id) {
        int result = recordMapper.deleteOne(id);
        DnsCache.loadRecords();
        return result;
    }

    @RequestMapping("/update")
    int updateOne(@RequestParam(name = "id") int id,
                  @RequestParam(name = "domain") String domain,
                  @RequestParam(name = "ip") String ip) {
        if (!Util.isIPv4(ip)) {
            return 0;
        } else {
            int result = recordMapper.UpdateOne(new RecordEntity(id, domain, ip));
            DnsCache.loadRecords();
            return result;
        }
    }
}
