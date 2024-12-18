package com.andersen.bookstore.starters.jpastarter;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(BookstoreJpaProperties.class)
public class BookstoreJpaAutoConfiguration {

    @Autowired
    private BookstoreJpaProperties properties;

    @Bean
    public DataSource dataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(properties.url());
        dataSource.setUser(properties.dbUsername());
        dataSource.setPassword(properties.dbPassword());
        return dataSource;
    }
}
