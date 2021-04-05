package cn.heyueyuan.ibranchwiki.jobs;

import cn.heyueyuan.ibranchwiki.entity.Ebook;
import cn.heyueyuan.ibranchwiki.service.EbookSnapshotService;
import cn.heyueyuan.ibranchwiki.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class EbookSnapshotJob {

    private static final Logger LOG = LoggerFactory.getLogger(EbookSnapshotJob.class);

    @Resource
    private EbookSnapshotService ebookSnapshotService;

    @Resource
    private SnowFlake snowFlake;

    public void doSnapshot() {
        MDC.put("LOG_ID", String.valueOf(SnowFlake.nextId()));
        LOG.info("生成今日电子书快照开始");
        Long start = System.currentTimeMillis();
        ebookSnapshotService.genSnapshot();
        LOG.info("生成今日电子书快照结束，耗时：{}毫秒", System.currentTimeMillis() - start);

    }
}
