package com.microservices.order.service;

import com.microservices.order.client.InventoryClient;
import com.microservices.order.dto.OrderRequest;
import com.microservices.order.dto.OrderResponse;
import com.microservices.order.model.Order;
import com.microservices.order.repository.OrderRepository;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public OrderService(OrderRepository orderRepository, InventoryClient inventoryClient) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
    }

    public OrderResponse placeOrder(OrderRequest orderRequest) {
        var isProductInStock = inventoryClient.isInStock(orderRequest.code(), orderRequest.quantity());

        if (isProductInStock) {
            Order order =
                orderRepository.save(new Order(UUID.randomUUID().toString(), orderRequest.code(), orderRequest.price(),
                    orderRequest.quantity()));

            return new OrderResponse(order.getId(), order.getOrderNumber(), orderRequest.code(), order.getPrice(),
                order.getQuantity());
        } else {
            throw new RuntimeException("Product with code " + orderRequest.code() + " is not in stock");
        }
    }
}
