package netty.dns.web;

import netty.dns.dao.Records;
import netty.dns.dao.RecordsMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController("/record")
public class DnsRecords {
    @Resource
    RecordsMapper recordsMapper;

    @RequestMapping("/all")
    List<Records> selectAll() {
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
        return recordsMapper.UpdateOne(new Records(id, domain, ip));
    }
}
