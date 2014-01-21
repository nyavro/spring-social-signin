package net.nyavro.spring.social.signinmvc.config;

import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class PersistenceContext {

    private static final String[] PROPERTY_PACKAGES_TO_SCAN = {
        "net.nyavro.spring.social.signinmvc.model"
    };
    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";

    @Value("${db.driver}")
    private String driver;

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String user;

    @Value("${db.password}")
    private String password;

    @Value("${hibernate.dialect}")
    private String dialect;

    @Value("${hibernate.format_sql}")
    private String format;

    @Value("${hibernate.hbm2ddl.auto}")
    private String autoHbm2ddl;

    @Value("${hibernate.ejb.naming_strategy}")
    private String naming;

    @Value("${hibernate.ejb.naming_strategy}")
    private String showSql;

    @Bean
    public DataSource dataSource() {
        final BoneCPDataSource source = new BoneCPDataSource();
        source.setDriverClass(driver);
        source.setJdbcUrl(url);
        source.setUsername(user);
        source.setPassword(password);
        return source;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        final JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactory().getObject());
        return manager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.setPackagesToScan(PROPERTY_PACKAGES_TO_SCAN);
        factory.setJpaProperties(properties());
        return factory;
    }

    private Properties properties() {
        final Properties properties = new Properties();
        properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, dialect);
        properties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL, format);
        properties.put(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO, autoHbm2ddl);
        properties.put(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY, naming);
        properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, showSql);
        return properties;
    }
}
