package com.microservices.inventory;

import com.microservices.inventory.service.InventoryService;
import java.util.Arrays;
import java.util.List;
import org.apache.naming.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(InventoryServiceApplication.class, args);

        InventoryService inventoryService = context.getBean(InventoryService.class);

        System.out.println(inventoryService.getClass().getName());

        //
        //ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        //
        //List<String> beanNames = Arrays.stream(beanFactory.getBeanDefinitionNames()).toList();
        //
        //System.out.println("Number of beans: " + beanNames.size());
        //
        //beanNames.forEach(System.out::println);
    }
}
