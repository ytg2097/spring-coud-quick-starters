package io.ac.starter.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-10
 **/
@ConfigurationProperties(prefix = "ac.web")
@Getter
@Setter
public class WebCoreProperties {

    @Value("${spring.application.name}")
    private String applicationName;

    private final Cors cors = new Cors();

    private final Idempotency idempotency = new Idempotency();

    @Getter
    @Setter
    public static class Cors {

        private String path = "/**";

        private String origin = "*";

        private String header = "*";

        private String method = "*";
    }

    @Getter
    @Setter
    public static class Idempotency {

        private String path = "/**";

    }
}
