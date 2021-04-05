package cn.heyueyuan.ibranchwiki.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * LOGInterceptor：/login
 */
@Component
public class LOGInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(LOGInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 打印请求信息
        LOG.info("------------- LogInterceptor Start -------------");
        LOG.info("Request address: {} {}", request.getRequestURL().toString(), request.getMethod());
        LOG.info("Remote address: {}", request.getRemoteAddr());

        long startTime = System.currentTimeMillis();
        request.setAttribute("requestStartTime", startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (Long) request.getAttribute("requestStartTime");
        LOG.info("------------- LogInterceptor End Time：{} ms -------------", System.currentTimeMillis() - startTime);
    }
}
