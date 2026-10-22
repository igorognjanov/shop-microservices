package com.microservices.inventory;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryServiceApplicationTests {

    @ServiceConnection
    private static PostgreSQLContainer container = new PostgreSQLContainer("postgres:17");

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    static {
        container.start();
    }

    @Test
    void checkInventoryFalse() {
        RestAssured.given()
            .param("code", "iphone_15")
            .param("quantity", 18)
            .contentType("application/json")
            .when()
            .get("/api/inventory")
            .then()
            .statusCode(200)
            .body(Matchers.equalTo("false"));
    }

    @Test
    void checkInventoryTrue() {

        RestAssured.given()
            .param("code", "iphone_15")
            .param("quantity", 2)
            .contentType("application/json")
            .when()
            .get("/api/inventory")
            .then()
            .statusCode(200)
            .body(Matchers.equalTo("true"));
    }
}
