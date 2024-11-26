package com.andersen.bookstore.services;

import com.andersen.bookstore.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private List <Order> orders;

    @Autowired
    public OrderService(List <Order> orders){
        this.orders = orders;
    }

    public List<Order> fetchOrders() {
        return orders;
    }
}
