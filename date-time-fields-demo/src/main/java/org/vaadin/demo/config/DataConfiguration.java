package org.vaadin.demo.config;

/**
 * Created by huth on 08.06.2017.
 */

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = {"org.vaadin.demo.repositories"})
@EnableTransactionManagement
@ComponentScan(basePackages = {
        "org.vaadin.demo.model"
})
@PropertySource("classpath:jdbc.properties")
@EnableAspectJAutoProxy
public class DataConfiguration {

    @Autowired
    Environment evn;

    @Bean
    public DataSource dataSource(){
        final DriverManagerDataSource dataSource=new DriverManagerDataSource();
        String username=evn.getProperty("jdbc.username");
        String password=evn.getProperty("jdbc.password");
        String url=evn.getProperty("jdbc.url");
        String driverClassName=evn.getProperty("jdbc.driverClassName");

        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);

        return dataSource;

    }

    @Bean
    public EntityManagerFactory entityManagerFactory(){
        final HibernateJpaVendorAdapter vendorAdapter=new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        final Properties jpaProperties=new Properties();
        jpaProperties.put("showSql", evn.getProperty("showSql"));
        jpaProperties.put("hibernate.hbm2ddl.auto", evn.getProperty("hibernate.hbm2ddl.auto"));
        jpaProperties.put("hibernate.dialect", evn.getProperty("hibernate.dialect"));//""");

        final LocalContainerEntityManagerFactoryBean factory=new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setPackagesToScan(evn.getProperty("packages.to.scan"));
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setJpaProperties(jpaProperties);
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        final JpaTransactionManager txManager=new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

    @Profile("!dbClean")
    @Bean(initMethod = "migrate" )
    public Flyway flywayNotADestroyer() {
        final Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource());
        flyway.setBaselineOnMigrate(true);
        return flyway;
    }

    @Profile("dbClean")
    @Bean(initMethod = "migrate")
    public Flyway flywayTheDestroyer() {
        final Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource());
        flyway.setBaselineOnMigrate(true);
        flyway.clean();
        return flyway;
    }


}
