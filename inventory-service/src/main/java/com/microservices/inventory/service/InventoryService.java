package com.microservices.inventory.service;

import com.microservices.inventory.repository.InventoryRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class InventoryService implements BeanNameAware, ApplicationContextAware, InitializingBean {

    private InventoryRepository repository;

    private String beanName;
    private ApplicationContext applicationContext;

    @Async
    public boolean isInStock(String code, Integer quantity) {
        return repository.existsByCodeAndQuantityGreaterThan(code, quantity);
    }

    @Autowired
    public void setRepository(InventoryRepository repository) {
        this.repository = repository;
        List<Long> list = new ArrayList<>();

    }

    @Override
    public void setBeanName(String name) {
        System.out.println("My name is " + name);
        this.beanName = name;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        System.out.println("Look! I have my context");
    }

    @PostConstruct  // 1. Runs FIRST
    public void postConstruct() {
        System.out.println("1. @PostConstruct");
    }

    @Override
    public void afterPropertiesSet() {  // 2. Runs SECOND
        System.out.println("2. InitializingBean.afterPropertiesSet()");
    }

    // 3. Custom init method (configured in @Bean or XML) runs THIRD
    public void customInit() {
        System.out.println("3. Custom init method");
    }
}
