package com.predictz.predictz.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
//@PropertySource({"classpath:application.yml"})
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.predictz.predictz.repository.solidpay",
        entityManagerFactoryRef = "solidpayEntityManager",
        transactionManagerRef = "solidpayTransactionManager")
public class SolidpayAutoConfiq {
    @Primary
    @Bean(name = "solidpayDataSource")
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource solidpayDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "solidpayEntityManager")
    public LocalContainerEntityManagerFactoryBean solidpayEntityManager(
            EntityManagerFactoryBuilder builder, @Qualifier("solidpayDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages("com.predictz.predictz.model.solidpay").persistenceUnit("solidpay")
                .build();
    }

    @Primary
    @Bean(name = "solidpayTransactionManager")
    public PlatformTransactionManager solidpayTransactionManager(
            @Qualifier("solidpayEntityManager") EntityManagerFactory solidpayEntityManager) {
        return new JpaTransactionManager(solidpayEntityManager);
    }
}
