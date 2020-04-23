package io.ac.starter.autoconfigure;

import io.ac.starter.swagger.SwaggerReSourcesProvider;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Collectors.toList;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-10
 **/
@Configuration
@ConditionalOnClass(WebMvcConfigurationSupport.class)
@EnableConfigurationProperties(SwaggerProperties.class)
@EnableSwagger2
public class SwaggerAutoConfiguration {

    private static Logger LOGGER = LoggerFactory.getLogger(SwaggerAutoConfiguration.class);
    @Autowired
    private SwaggerProperties swaggerProperties;

    @Bean
    public Docket openApi() {

        LOGGER.info("Swagger registered in the container");

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(swaggerProperties.getSwagger().getEnable())
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getSwagger().getScanPackage()))
                .apis(requestHandler -> requestHandler.findAnnotation(ApiOperation.class).isPresent())
                .paths(PathSelectors.any())
                .build().globalOperationParameters(headers());
    }

    private List<Parameter> headers() {

        List<SwaggerProperties.SwaggerHeader> headers = swaggerProperties.getSwagger().getHeaders();
        return headers == null ? null : headers.stream().map(header ->
                new ParameterBuilder().name(header.getName()).description(header.getDesc())
                        .modelRef(new ModelRef(header.getType())).parameterType("header")
                        .required(header.getRequire()).build()
        ).collect(toList());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(swaggerProperties.getApplicationName() + "-API")
                .version(swaggerProperties.getSwagger().getVersion()).build();
    }

    @Bean
    public SwaggerReSourcesProvider swaggerResourcesProvider() {

        return new SwaggerReSourcesProvider();
    }
}


