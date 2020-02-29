package io.ac.starter.idempotency;

import common.WebConst;
import common.exception.RepetitiveOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-18
 **/
public class IdempotencyOperationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private OpTokenRepository opTokenRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) {
        String method = request.getMethod();

        if (needIdempotency(method)) {
            String opToken = request.getParameter(WebConst.REQUEST_HEADER_OP_TOKEN);

            if (opToken == null || opToken.trim().length() == 0) {// 意味着不是 Feign 的调用方式
                return true;
            }
            if (opTokenRepository.exsits(opToken)) {
                throw new RepetitiveOperationException();
            }
            opTokenRepository.store(opToken);
        }
        return true;
    }

    private boolean needIdempotency(String method) {
        HttpMethod httpMethod = HttpMethod.resolve(method);
        return HttpMethod.POST == httpMethod || HttpMethod.PUT == httpMethod || HttpMethod.DELETE == httpMethod;
    }
}

