package netty.dns.web;

import netty.dns.dao.LogsEntity;
import netty.dns.dao.LogsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController("/logs")
public class DnsLogs {
    @Resource
    LogsMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(DnsLogs.class);

    // http://127.0.0.1/getAll
    @RequestMapping("/getAll")
    List<LogsEntity> getAll(@RequestParam(name = "maxid") int id, @RequestParam(name = "ip") String ip) {/*
        logger.debug("debug");
        logger.error("error");
        logger.info("info");*/
        if (ip.length() < 11) {
            //System.out.println("all");
            return mapper.selectAll(id);
        } else {
            //System.out.println(ip+mapper.selectByIP(id, ip).toString());
            return  mapper.selectByIP(id, ip);
        }
    }

    // http://127.0.0.1/insert
    @RequestMapping("/insert")
    long inertOne() {
        logger.info("已执行");
        LogsEntity logs = new LogsEntity("1", "2", "3", "4", "5");
        mapper.insertOne(logs);
        return logs.getId();
    }
}
