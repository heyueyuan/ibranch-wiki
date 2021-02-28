package cn.heyueyuan.ibranchwiki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("cn.heyueyuan.ibranchwiki.mapper")
@EnableScheduling
@EnableAsync
public class IbranchWikiApplication {

    private static final Logger LOG = LoggerFactory.getLogger(IbranchWikiApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(IbranchWikiApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("Running！！");
        LOG.info("Address: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }

}
