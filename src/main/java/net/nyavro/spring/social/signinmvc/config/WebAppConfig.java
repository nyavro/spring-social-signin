package net.nyavro.spring.social.signinmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"net.nyavro.spring.social.signinmvc.controller"})
@EnableWebMvc
public class WebAppConfig extends WebMvcConfigurerAdapter {

    private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/jsp/";
    private static final String VIEW_RESOLVER_SUFFIX = ".jsp";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public SimpleMappingExceptionResolver exceptionResolver() {
        final SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        exceptionResolver.setExceptionMappings(exceptionMappings());
        exceptionResolver.setStatusCodes(statusCodes());
        return exceptionResolver;
    }

    private Properties exceptionMappings() {
        final Properties mappings = new Properties();
        mappings.put("java.lang.Exception", "error/error");
        mappings.put("java.lang.RuntimeException", "error/error");
        return mappings;
    }

    private Properties statusCodes() {
        final Properties codes = new Properties();
        codes.put("error/404", "404");
        codes.put("error/error", "500");
        return codes;
    }

    @Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix(VIEW_RESOLVER_PREFIX);
        resolver.setSuffix(VIEW_RESOLVER_SUFFIX);
        return resolver;
    }
}
