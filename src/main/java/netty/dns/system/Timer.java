package netty.dns.system;

import netty.dns.server.DnsCache;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Timer {

    //[秒] [分] [小时] [日] [月] [周] [年]
    //https://www.jianshu.com/p/1defb0f22ed1

    //程序启动是延迟一秒启动,之后每2小时启动一次 2*3600*1000
    @Scheduled(cron = "0 0 */2 * * ?")
    public void cleanAndUpdate(){
        DnsCache.loadRecords();
        System.out.println("定时器已启动");
    }
}
