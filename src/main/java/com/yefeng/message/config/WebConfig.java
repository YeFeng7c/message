//package com.yefeng.message.config;
//import com.yefeng.message.interceptor.TokenInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
//import org.springframework.web.servlet.config.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.Executors;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//@Configuration
//public class WebConfig implements WebMvcConfigurer{
//
//    /**
//     * 跨域请求支持/token拦截
//     * tip:只能写在一个配置类里
//     */
//        private TokenInterceptor tokenInterceptor;
//
//        //构造方法
//        public WebConfig(TokenInterceptor tokenInterceptor) {
//            this.tokenInterceptor = tokenInterceptor;
//        }
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        List<String> excludePath = new ArrayList<>();
//        //排除拦截，其他都拦截
//        excludePath.add("/user/login");  //登录
//        excludePath.add("/user/regist");     //注册
//        excludePath.add("/swagger-resources/**");
//        excludePath.add("/swagger-ui.html/**");
//        excludePath.add("/webjars/**");
//        excludePath.add("/v2/**");
//        excludePath.add("/static/**");  //静态资源
//        excludePath.add("/assets/**");  //静态资源
//
//        registry.addInterceptor(tokenInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns(excludePath);
//        WebMvcConfigurer.super.addInterceptors(registry);
//    }
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
//                .allowedHeaders("*")
//                .exposedHeaders("access-control-allow-headers",
//                        "access-control-allow-methods",
//                        "access-control-allow-origin",
//                        "access-control-max-age",
//                        "X-Frame-Options")
//                .allowCredentials(false).maxAge(3600);
//        WebMvcConfigurer.super.addCorsMappings(registry);
//    }
//        @Override
//        public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//            configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(3)));
//            configurer.setDefaultTimeout(30000);
//        }
//
//
//    }
