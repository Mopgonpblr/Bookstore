package com.andersen.bookstore.starters.inmemorydbstarter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties

public record InMemoryDbProperties(
        @Value("${filepath2}") String filepath2) {

}
