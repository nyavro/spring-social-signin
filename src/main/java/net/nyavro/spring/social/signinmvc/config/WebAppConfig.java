package net.nyavro.spring.social.signinmvc.config;

import com.google.common.collect.ImmutableMap;
import net.nyavro.spring.social.signinmvc.utils.JsonUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"net.nyavro.spring.social.signinmvc.controller", "com.nyavro.dienstleustigen.controller"})
@EnableWebMvc
public class WebAppConfig extends WebMvcConfigurerAdapter {

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
        final UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setViewClass(org.springframework.web.servlet.view.freemarker.FreeMarkerView.class);
        resolver.setSuffix(".ftl");
        resolver.setContentType("text/html;charset=UTF-8");
        resolver.setOrder(Ordered.LOWEST_PRECEDENCE);
        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer configurer() {
        final FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("/WEB-INF/freemarker/");
        configurer.setDefaultEncoding("UTF-8");
        final Properties properties = new Properties();
//        properties.put("cache_storage", "freemarker.cache.NullCacheStorage");
        configurer.setFreemarkerSettings(properties);
        configurer.setFreemarkerVariables(new ImmutableMap.Builder<String, Object>().put("utils", new JsonUtils()).build());
        return configurer;
    }
}
