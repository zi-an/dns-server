package netty.dns.web;

import netty.dns.dao.Record;
import netty.dns.dao.RecordMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/record")
public class DnsRecords {
    @Resource
    RecordMapper recordsMapper;

    @RequestMapping("/all")
    List<Record> selectAll() {
        return recordsMapper.selectAll();
    }

    @RequestMapping("/inert")
    int insertOne(@RequestParam(name = "domain") String domain,
                  @RequestParam(name = "ip") String ip) {
        return recordsMapper.insertOne(domain, ip);
    }

    @RequestMapping("/delete")
    int deleteOne(@RequestParam(name = "id") int id) {
        return recordsMapper.deleteOne(id);
    }

    @RequestMapping("/update")
    int updateOne(@RequestParam(name = "id") int id,
                  @RequestParam(name = "domain") String domain,
                  @RequestParam(name = "ip") String ip) {
        return recordsMapper.UpdateOne(new Record(id, domain, ip));
    }
}
