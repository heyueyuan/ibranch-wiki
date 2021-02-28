package cn.heyueyuan.ibranchwiki.controller;

import cn.heyueyuan.ibranchwiki.entity.Test;
import cn.heyueyuan.ibranchwiki.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping("/test")
    public List<Test> list() {
        return testService.list();
    }
}
