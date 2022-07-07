package cn.dns.sys;

import cn.dns.stat.DomainNameStat;
import cn.dns.stat.StatManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/*
 * 定时器 每晚写入统计数据
 * by zi-an
 *
 */
@Component
public class Scheduler {
    @Resource
    JdbcTemplate jdbcTemplate;

    //测试用
    //@Scheduled(cron = "*/5 * * * * *")
    @Scheduled(cron = "00 59 23 * * *")
    void updateTotal() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        //calendar.add(Calendar.DATE, -1);获取前一天
        String date = simpleDateFormat.format(calendar.getTime());
        System.out.println(date);

        Map<String, DomainNameStat> domainNameMap = StatManager.getInstance().getdomainNameMap();
        String sql = "insert into total(date,dnsname,count) value('" + date + "',?,?)";
        if (!domainNameMap.isEmpty()) {
            List<Object[]> batchArgs = new ArrayList<Object[]>();
            for (DomainNameStat domainNameStat : domainNameMap.values()) {
                batchArgs.add(new Object[]{domainNameStat.name, domainNameStat.queryCount});
            }
            jdbcTemplate.batchUpdate(sql, batchArgs);
            System.out.println(batchArgs.toString());
        } else {
            System.out.println("nul record");
        }

    }
}