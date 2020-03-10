package io.ac.starter.sleuth;

import common.JsonUtil;
import io.ac.starter.autoconfigure.SleuthProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import static io.ac.starter.autoconfigure.SleuthProperties.SWAGGER_URI1;
import static io.ac.starter.autoconfigure.SleuthProperties.SWAGGER_URI2;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-18
 **/
@RestControllerAdvice
public class RestResponseBodyAdvice implements ResponseBodyAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestResponseBodyAdvice.class);

    @Autowired
    private SleuthProperties sleuthProperties;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if(request.getURI().getPath().contains(SWAGGER_URI1) ||  request.getURI().getPath().contains(SWAGGER_URI2)){
            return body;
        }
        LOGGER.info(sleuthProperties.getName() + "-response:" + JsonUtil.toJson(body));
        return body;
    }
}
