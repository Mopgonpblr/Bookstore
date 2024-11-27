package com.andersen.bookstore.starters.jpastarter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties

public record BookstoreJpaProperties(
        @Value("${url}") String url,
        @Value("${dbusername}") String dbUsername,
        @Value("${dbpassword}") String dbPassword) {

}
