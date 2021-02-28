package cn.heyueyuan.ibranchwiki.service;

import cn.heyueyuan.ibranchwiki.entity.Test;
import cn.heyueyuan.ibranchwiki.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {

    @Resource
    private TestMapper testMapper;

    public List<Test> list() {
        return testMapper.list();
    }
}
