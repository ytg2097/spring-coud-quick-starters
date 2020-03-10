package io.ac.starter.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-18
 **/
@ConfigurationProperties(prefix = "spring.application")
@Getter
@Setter
public class SwaggerProperties {


    @Value("${spring.application.name}")
    private String applicationName;

    private final Swagger swagger = new Swagger();

    @Getter
    @Setter
    public static class Swagger{

        private Boolean enable = false;

        private String scanPackage = "";

        private String version = "1.0";
    }
}
