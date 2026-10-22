package com.microservices.order.dto;

import java.math.BigDecimal;

public record OrderRequest(String code, BigDecimal price, Integer quantity) {
}
