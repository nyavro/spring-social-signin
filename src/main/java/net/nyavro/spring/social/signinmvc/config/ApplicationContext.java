package net.nyavro.spring.social.signinmvc.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
@ComponentScan(basePackages = {
        "net.nyavro.spring.social.signinmvc.services"
})
@Import({WebAppConfig.class, MongoConfig.class, PersistenceConfig.class, SecurityConfig.class, SocialConfig.class})
@PropertySource("classpath:application.properties")
@EnableAspectJAutoProxy
public class ApplicationContext {

    private static final String MESSAGE_SOURCE_BASE_NAME = "i18n/messages";

    @Bean
    public MessageSource messageSource() {
        final ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename(MESSAGE_SOURCE_BASE_NAME);
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
