package io.ac.starter.operatorid;

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
public class OperatorIdHolder {

    public String getOperatorId() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new OperatorIdCannotBeNullException();
        } else {
            HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
            String operatorId = request.getHeader("operatorId");
            if (StringUtils.isEmpty(operatorId)) {
                throw new OperatorIdCannotBeNullException();
            } else {
                return operatorId;
            }
        }
    }

    public String findOperatorId() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        } else {
            HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
            String operatorId = request.getHeader("operatorId");
            return StringUtils.isEmpty(operatorId) ? null : operatorId;
        }
    }
}
