package com.yefeng.message;

import java.util.ArrayList;
import java.util.List;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Configuration
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private boolean SwaggerSwitch;

    public SwaggerConfig() {
    }

    public ApiInfo apiInfo() {
        return (new ApiInfoBuilder()).title("博客").description("博客—接口文档").version("1.0").build();
    }

    @Bean
    public Docket creatResyDocket() {
        log.info("======================== 当前环境是否开启Swagger：" + SwaggerSwitch + " ========================");
        return (new Docket(DocumentationType.SWAGGER_2))
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .enable(SwaggerSwitch)
                .apiInfo(this.apiInfo())
                .select().apis(RequestHandlerSelectors.basePackage("com.yefeng.message.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private List<ApiKey> securitySchemes() {
        ApiKey apiKey = new ApiKey("Authorization", "token", "header");
        ArrayList arrayList = new ArrayList();
        arrayList.add(apiKey);
        return arrayList;
    }


    /**
     * 在Swagger2的securityContexts中通过正则表达式，设置需要使用参数的接口（或者说，是去除掉不需要使用参数的接口），
     * 如下列代码所示，通过PathSelectors.regex("^(?!auth).*$")，
     * 所有包含"auth"的接口不需要使用securitySchemes。即不需要使用上文中设置的名为“Authorization”，
     * type为“header”的参数。
     *
     */
    private List<SecurityContext> securityContexts() {
        SecurityContext build = SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
        ArrayList arrayList = new ArrayList();
        arrayList.add(build);
        return arrayList;
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        SecurityReference authorization = new SecurityReference("Authorization", authorizationScopes);
        ArrayList arrayList = new ArrayList<>();
        arrayList.add(authorization);
        return arrayList;
    }
}