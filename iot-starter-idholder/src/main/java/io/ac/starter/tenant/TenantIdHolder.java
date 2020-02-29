package io.ac.starter.tenant;

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
public class TenantIdHolder {

    public String getTid() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new TenantCannotBeNullException();
        } else {
            HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
            String tid = request.getHeader("tid");
            if (StringUtils.isEmpty(tid)) {
                throw new TenantCannotBeNullException();
            } else {
                return tid;
            }
        }
    }

    public String findTid() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        } else {
            HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
            String tid = request.getHeader("tid");
            return StringUtils.isEmpty(tid) ? null : tid;
        }
    }
}
