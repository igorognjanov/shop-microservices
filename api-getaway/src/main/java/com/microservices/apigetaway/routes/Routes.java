package com.microservices.apigetaway.routes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {
        return GatewayRouterFunctions.route("product_service")
            .route(RequestPredicates.path("/api/product"), HandlerFunctions.http())
            .before(BeforeFilterFunctions.uri("http://127.0.0.1:8083"))
            .build();
    }
    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoute() {
        class Solution {
            public List<List<Integer>> threeSum(int[] nums) {
                System.out.println();
                Set<List<Integer>> result = new HashSet<List<Integer>>();
                List<List<Integer>> ss = new ArrayList<List<Integer>>();
                ss.sort((a, b) -> a.compareT);
                for (int i = 0; i < nums.length; i++) {
                    for (int j = 0; j < nums.length; j++) {
                        if (i != j && set.contains(- i - j)){}
                    }
                }

            }
        }
        return GatewayRouterFunctions.route("inventory_service")
            .route(RequestPredicates.path("/api/inventory"), HandlerFunctions.http())
            .before(BeforeFilterFunctions.uri("http://127.0.0.1:8082"))
            .build();
    }
    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute() {
        return GatewayRouterFunctions.route("order_service")
            .route(RequestPredicates.path("/api/order"), HandlerFunctions.http())
            .before(BeforeFilterFunctions.uri("http://127.0.0.1:8081"))
            .build();
    }
}
