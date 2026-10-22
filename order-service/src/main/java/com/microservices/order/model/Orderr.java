package com.microservices.order.model;

import java.math.BigDecimal;

public class Orderr {
    private Long id;

    public Orderr() {}

    public Orderr(String orderNumber, String code, BigDecimal price, Integer quantity) {
        this.orderNumber = orderNumber;
        this.code = code;
        this.price = price;
        this.quantity = quantity;
    }

    public String getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    private String orderNumber;
    private String code;
    private BigDecimal price;
    private Integer quantity;
}
