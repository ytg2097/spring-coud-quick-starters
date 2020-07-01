package io.ac.starter.util;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-18
 **/
public class RequestHeadHolder {

    public enum RequestHead{

        TENANT_ID("tid"),
        LOGIN_NAME("operatorName"),
        OPERATOR_ID("operatorId");
        private String name;

        RequestHead(String name) {
            this.name = name;
        }

    }

    public static String get(RequestHead head) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new RequestHeadCannotBeNullException(head.name);
        } else {
            HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
            String tid = request.getHeader(head.name);
            if (StringUtils.isEmpty(tid)) {
                throw new RequestHeadCannotBeNullException(head.name);
            } else {
                return tid;
            }
        }
    }

    public static String find(RequestHead head) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        } else {
            HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
            String tid = request.getHeader(head.name);
            return StringUtils.isEmpty(tid) ? null : tid;
        }
    }
}
