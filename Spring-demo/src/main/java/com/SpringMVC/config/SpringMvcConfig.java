package com.SpringMVC.config;

import com.SpringMVC.interceptor.ProcessInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc // json数据处理,必须使用此注解,因为他会加入json处理器
@ComponentScan(basePackages = {"com.SpringMVC.controller", "com.SpringMVC.exception"})
// WebMvcConfigurer：springMvc进行组件配置的规范,配置组件,提供各种方法!
public class SpringMvcConfig implements WebMvcConfigurer {

    @Override // 添加拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        // 将拦截器添加到Springmvc环境,默认拦截所有Springmvc分发的请求
        // 精准匹配,设置拦截器处理指定请求 路径可以设置一个或者多个,为项目下路径即可
        // addPathPatterns("/common/request/one") 添加拦截路径
        // 也支持 /* 和 /** 模糊路径。 * 任意一层字符串 ** 任意层 任意字符串
        registry.addInterceptor(new ProcessInterceptor())
                .addPathPatterns("/common/request/one","/common/request/tow")
                .excludePathPatterns("/common/request/tow");
    }
}
