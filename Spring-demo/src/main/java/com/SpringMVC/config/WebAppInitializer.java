package com.SpringMVC.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// SpringMVC提供的接口,是替代web.xml的方案,更方便实现完全注解方式ssm处理!
// Springmvc框架会自动检查当前类的实现类,会自动加载 getRootConfigClasses / getServletConfigClasses 提供的配置类
// getServletMappings 返回的地址 设置DispatherServlet对应处理的地址
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 指定service/mapper层的配置类
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{ServletConfig.class};
    }

    /**
     * 指定springmvc的配置类
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{SpringMvcConfig.class};
    }

    /**
     * 设置dispatcherServlet的处理路径!
     * 一般情况下为 / 代表处理所有请求!
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}