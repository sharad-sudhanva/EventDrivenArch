package com.example.order_service.controller;

import com.example.order_service.dto.Order;
import com.example.order_service.dto.OrderEvent;
import com.example.order_service.kafka.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/ctr/")
@RequiredArgsConstructor
public class OrderController {

    private final OrderProducer producer;

    @PostMapping("/orders")
    public String orderPlaced(@RequestBody Order order){

        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent event = new OrderEvent(order.getOrderId(), order.getQty());

        producer.sendMessage(event);

        return "Order placed";
    }

}
