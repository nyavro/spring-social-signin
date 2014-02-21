package net.nyavro.spring.social.signinmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


@Configuration
@ComponentScan(basePackages = {
        "net.nyavro.spring.social.signinmvc.services"
})
@Import({MongoConfig.class, SecurityTestConfig.class})
@PropertySource("classpath:application-test.properties")
@EnableAspectJAutoProxy
public class ApplicationTestContext {

    @Bean
    public PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}