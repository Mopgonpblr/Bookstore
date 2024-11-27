package com.andersen.bookstore.starters.inmemorydbstarter;

import com.andersen.bookstore.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


@Configuration
@EnableConfigurationProperties(InMemoryDbProperties.class)
public class InMemoryDbAutoConfig {

    private InMemoryDbProperties properties;

    @Autowired
    public InMemoryDbAutoConfig(InMemoryDbProperties properties){
        this.properties = properties;
    }

    @Bean
    public List<Order> orders() {
        List<Order> orders = new LinkedList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            Scanner scanner = new Scanner(new File(properties.filepath2()));
            if (scanner.hasNextLine()) {
                orders = objectMapper.readValue(scanner.nextLine(), new TypeReference<>() {
                });
            }
            scanner.close();
        } catch (JsonProcessingException | FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return orders;
    }

}