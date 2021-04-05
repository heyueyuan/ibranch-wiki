package cn.heyueyuan.ibranchwiki.service;

import cn.heyueyuan.ibranchwiki.mapper.EbookSnapshotMapper;
import cn.heyueyuan.ibranchwiki.mapper.EbookSnapshotMapperCust;
import cn.heyueyuan.ibranchwiki.response.StatisticResp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookSnapshotService {

    @Resource
    private EbookSnapshotMapperCust ebookSnapshotMapperCust;

    public void genSnapshot() {
        ebookSnapshotMapperCust.genSnapshot();
    }

    public List<StatisticResp> getStatistic() {
        return ebookSnapshotMapperCust.getStatistic();
    }

    public List<StatisticResp> get30Statistic() {
        return ebookSnapshotMapperCust.get30Statistic();
    }
}
