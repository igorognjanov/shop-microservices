package com.microservices.order.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "orders")
@Entity
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Order(String orderNumber, String code, BigDecimal price, Integer quantity) {
        this.orderNumber = orderNumber;
        this.code = code;
        this.price = price;
        this.quantity = quantity;
    }

    private String orderNumber;
    private String code;
    private BigDecimal price;
    private Integer quantity;
}
