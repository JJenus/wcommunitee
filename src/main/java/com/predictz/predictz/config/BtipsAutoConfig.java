package com.predictz.predictz.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.predictz.predictz.repository.btips",
        entityManagerFactoryRef = "btipsEntityManager",
        transactionManagerRef = "btipsTransactionManager")
public class BtipsAutoConfig {
    @Bean(name = "btipsDataSource")
    @ConfigurationProperties(prefix="spring.s-datasource")
    public DataSource btipsDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "btipsEntityManager")
    public LocalContainerEntityManagerFactoryBean btipsEntityManager(
            EntityManagerFactoryBuilder builder, @Qualifier("btipsDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages("com.predictz.predictz.model.btips").persistenceUnit("btips")
                .build();
    }

    @Bean(name = "btipsTransactionManager")
    public PlatformTransactionManager btipsTransactionManager(
            @Qualifier("btipsEntityManager") EntityManagerFactory btipsEntityManager) {
        return new JpaTransactionManager(btipsEntityManager);
    }
}
