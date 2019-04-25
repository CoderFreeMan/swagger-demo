package com.ydj.swaggerdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Value("${swagger.enable}")
    private boolean enableSwagger;

    @Bean
    public Docket createRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(enableSwagger)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ydj.swaggerdemo.controller"))
                .paths(PathSelectors.regex("^(?!lg).*$"))
                .paths(PathSelectors.any())
                .build();
        docket.securitySchemes(securitySchemes());
        docket.securityContexts(securityContexts());
        return docket;
    }

    /**
     * 安全
     * @return
     */
    private List<ApiKey> securitySchemes() {
        List<ApiKey> ls = new ArrayList<>();
        ls.add(new ApiKey("Authorization", "HOPSON_SESSION_ID", "header"));
        ls.add(new ApiKey("Authorization1", "USER_ID", "header"));
        ls.add(new ApiKey("Authorization2", "TOKEN", "header"));
        ls.add(new ApiKey("Authorization3", "CLIENT_TYPE", "header"));
        ls.add(new ApiKey("Authorization4", "GROUP_ID", "header"));
        ls.add(new ApiKey("Authorization5", "COMMUNITY_ID", "header"));
        return ls;
    }

    /**
     * 安全
     * @return
     */
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> ls = new ArrayList<>();
        ls.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!login).*$"))
                .build());
        return ls;
    }

    /**
     * 安全
     * @return
     */
    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> ls = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        ls.add(new SecurityReference("Authorization", authorizationScopes));
        ls.add(new SecurityReference("Authorization1", authorizationScopes));
        ls.add(new SecurityReference("Authorization2", authorizationScopes));
        ls.add(new SecurityReference("Authorization3", authorizationScopes));
        ls.add(new SecurityReference("Authorization4", authorizationScopes));
        ls.add(new SecurityReference("Authorization5", authorizationScopes));
        return ls;
    }

    /**
     * 文档描述
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("文档标题(和工程服务相关)")
                .description("描述(和工程业务相关)")
                .termsOfServiceUrl("Swagger UI 暴露地址, eg. http://127.0.0.1:8080/swagger-ui.html")
                .version("文档版本")
                .build();
    }
}
