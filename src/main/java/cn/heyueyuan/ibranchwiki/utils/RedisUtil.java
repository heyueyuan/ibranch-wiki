package cn.heyueyuan.ibranchwiki.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    private static final Logger LOG = LoggerFactory.getLogger(RedisUtil.class);

    @Resource
    private RedisTemplate redisTemplate;

    public boolean validateRepeat(String key, long second) {
        if (redisTemplate.hasKey(key)) {
            LOG.info("key already existed: {}", key);
            return false;
        } else {
            LOG.info("key not existed, {}, expired in {}s", key, second);
            redisTemplate.opsForValue().set(key, key, second, TimeUnit.SECONDS);
            return true;
        }
    }
}
