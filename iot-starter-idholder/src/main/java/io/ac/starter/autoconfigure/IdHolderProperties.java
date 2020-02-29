package io.ac.starter.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-18
 **/
@ConfigurationProperties(
        prefix = "spring.application"
)
public class IdHolderProperties {

    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
