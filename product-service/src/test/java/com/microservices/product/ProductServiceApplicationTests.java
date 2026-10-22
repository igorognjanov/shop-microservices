package com.microservices.product;

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
class ProductServiceApplicationTests {

    @ServiceConnection
    static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:17");

    @LocalServerPort
    private int port;

    static {
        postgreSQLContainer.start();
    }

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void shouldCreateProduct() {
        String requestBody = """        
            {"id": 1,"name": "test name", "description": "desc", "price": 6565}
            """;

        RestAssured.given()
            .contentType("application/json")
            .body(requestBody)
            .when()
            .post("/api/product")
            .then()
            .statusCode(201)
            .body("id", Matchers.notNullValue())
            .body("name", Matchers.equalTo("test name"))
            .body("description", Matchers.equalTo("desc"))
            .body("price", Matchers.equalTo(6565));

    }
}
