package io.ac.starter.corsconfig;

import io.ac.starter.autoconfigure.WebCoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-10
 **/
public class CorsConfig {

    private static Logger LOGGER = LoggerFactory.getLogger(CorsConfig.class);
    private WebCoreProperties.Cors cors;

    public CorsConfig(WebCoreProperties.Cors cors) {
        this.cors = cors;
    }

    public CorsFilter corsFilter() {

        LOGGER.info("Cors config registered in the container");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(cors.getPath(), buildConfig());
        return new CorsFilter(source);
    }

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin(cors.getOrigin());
        corsConfiguration.addAllowedHeader(cors.getHeader());
        corsConfiguration.addAllowedMethod(cors.getMethod());
        return corsConfiguration;
    }
}
