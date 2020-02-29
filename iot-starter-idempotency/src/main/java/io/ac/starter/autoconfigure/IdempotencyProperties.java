package io.ac.starter.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-18
 **/
@ConfigurationProperties(prefix = "io.ac.iot.idempotency")
public class IdempotencyProperties {

    private String appName;

    private int timeoutOfOpToken = 3 * 20 * 1000;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getTimeoutOfOpToken() {
        return timeoutOfOpToken;
    }

    public void setTimeoutOfOpToken(int timeoutOfOpToken) {
        this.timeoutOfOpToken = timeoutOfOpToken;
    }
}