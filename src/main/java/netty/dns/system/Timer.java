package netty.dns.system;

import netty.dns.dao.Records;
import netty.dns.dao.RecordsMapper;
import netty.dns.server.DnsHandler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class Timer {
    @Resource
    RecordsMapper recordsMapper;

    //[秒] [分] [小时] [日] [月] [周] [年]
    //https://www.jianshu.com/p/1defb0f22ed1

    //程序启动是延迟一秒启动,之后每2小时启动一次 2*3600*1000
    @Scheduled(initialDelay = 1000,fixedRate = 2*3600*1000 )
    public void cleanAndUpdate(){
        List<Records> records=recordsMapper.selectAll();
        System.out.println(records );
        System.out.println("定时器已启动");
    }
}
