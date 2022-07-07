package cn.dns.app;

import cn.dns.protocol.RecursiveResolver;
import cn.dns.util.BeanUtils;
import cn.dns.util.Configs;
import cn.dns.protocol.NameServer;
import cn.dns.protocol.RuleManager;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by matrixy on 2019/4/19.
 */
@EnableScheduling
@ComponentScan(value = {"cn"})
@SpringBootApplication
@MapperScan("cn.dns")
public class DNSCheaterApp
{
    static Logger logger = LoggerFactory.getLogger(DNSCheaterApp.class);

    public static void main(String[] args) throws Exception
    {
        ApplicationContext context = SpringApplication.run(DNSCheaterApp.class, args);
        BeanUtils.init(context);
        Configs.init(context);

        RuleManager.getInstance().init();
        NameServer.getInstance().init();
        RecursiveResolver.getInstance().init();
    }
}
