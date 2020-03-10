package io.ac.starter.sleuth;

import common.HttpUtil;
import io.ac.starter.autoconfigure.SleuthProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static io.ac.starter.autoconfigure.SleuthProperties.SWAGGER_URI1;
import static io.ac.starter.autoconfigure.SleuthProperties.SWAGGER_URI2;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-18
 **/
@Component
public class SleuthInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SleuthInterceptor.class);

    @Autowired
    private SleuthProperties sleuthProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if(request.getRequestURI().contains(SWAGGER_URI1) ||  request.getRequestURI().contains(SWAGGER_URI2)){
            return true;
        }
        LOGGER.info(sleuthProperties.getName() + "-request:" + HttpUtil.requestToStr(request));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
    }
}

