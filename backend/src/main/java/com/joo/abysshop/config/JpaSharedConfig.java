package com.joo.abysshop.config;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JpaSharedConfig {

    @Bean(name = "jpaSharedEM_entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean sharedEntityManagerFactory(
        EntityManagerFactoryBuilder builder,
        DataSource dataSource
    ) {
        return builder
            .dataSource(dataSource)
            .packages("com.joo.abysshop.entity")
            .persistenceUnit("default")
            .build();
    }

    @Bean(name = "jpaSharedEM_transactionManager")
    public PlatformTransactionManager sharedTransactionManager(
        @Qualifier("jpaSharedEM_entityManagerFactory") EntityManagerFactory emf
    ) {
        return new JpaTransactionManager(emf);
    }
}
