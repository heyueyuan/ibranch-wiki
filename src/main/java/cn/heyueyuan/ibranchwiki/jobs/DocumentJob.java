package cn.heyueyuan.ibranchwiki.jobs;

import cn.heyueyuan.ibranchwiki.service.DocumentService;
import cn.heyueyuan.ibranchwiki.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DocumentJob {

    private static final Logger LOG = LoggerFactory.getLogger(DocumentJob.class);

    @Resource
    private DocumentService documentService;

    @Resource
    private SnowFlake snowFlake;

    @Scheduled(cron = "5/30 * * * * ?")
    public void cron() {
        MDC.put("LOG_ID", String.valueOf(SnowFlake.nextId()));
        LOG.info("更新电子书下的文档数据开始");
        long start = System.currentTimeMillis();
        documentService.updateEbookInfo();
        LOG.info("更新电子书下的文档数据结束，耗时：{}毫秒", System.currentTimeMillis() - start);
    }


}
