package net.nyavro.spring.social.signinmvc.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.net.UnknownHostException;

@Configuration
@EnableMongoRepositories("net.nyavro.spring.social.signinmvc")
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${mongo.db.host}")
    private String mongoHost;

    @Value("${mongo.db.port}")
    private int mongoPort;

    @Value("${mongo.db.name}")
    private String mongoDatabaseName;

    @Override
    protected String getDatabaseName() {
        return mongoDatabaseName;
    }

    public Mongo mongo() throws UnknownHostException {
        return new MongoClient(mongoHost, mongoPort);
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public UserValidation userValidation() {
        return new UserValidation();
    }

    @Bean
    @Override
    public MongoTemplate mongoTemplate() throws UnknownHostException {
        return new MongoTemplate(mongo(), mongoDatabaseName);
    }
}
