package cn.heyueyuan.ibranchwiki.mapper;

import org.apache.ibatis.annotations.Param;

public interface DocumentMapperCust {
    public void increaseViewCount(@Param("id") Long id);
    public void increaseVoteCount(@Param("id") Long id);

    public void updateEbookInfo();
}
