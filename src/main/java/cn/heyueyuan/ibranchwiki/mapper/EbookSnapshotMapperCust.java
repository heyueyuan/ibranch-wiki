package cn.heyueyuan.ibranchwiki.mapper;

import cn.heyueyuan.ibranchwiki.response.StatisticResp;

import java.util.List;

public interface EbookSnapshotMapperCust {

    public void genSnapshot();

    List<StatisticResp> getStatistic();

    List<StatisticResp> get30Statistic();
}
