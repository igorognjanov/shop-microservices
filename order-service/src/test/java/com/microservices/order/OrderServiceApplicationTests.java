package com.microservices.order;

import com.microservices.order.client.InventoryClient;
import com.microservices.order.stubs.InventoryClientStub;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class OrderServiceApplicationTests {

    @ServiceConnection
    private static PostgreSQLContainer container = new PostgreSQLContainer("postgres:17");

    @LocalServerPort
    private int port;

    static {
        container.start();
    }

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void createOrderFailed() {
        String body = """
            {"code": "iphone_15",
             "price": 432,
             "quantity": 22
            }
            """;
        InventoryClientStub.stubCallForInventory("iphone_15", 22, false);
        RestAssured.given()
            .body(body)
            .contentType("application/json")
            .when()
            .post("/api/order")
            .then()
            .statusCode(500)
            .body("message", Matchers.equalTo("Product with code iphone_15 is not in stock"));
    }

    @Test
    void createOrderSuccess() {
        String body = """
            {"code": "iphone_15",
             "price": 432,
             "quantity": 1
            }
            """;
        InventoryClientStub.stubCallForInventory("iphone_15", 1, true);
        RestAssured.given()
            .body(body)
            .contentType("application/json")
            .when()
            .post("/api/order")
            .then()
            .statusCode(201)
            .body("code", Matchers.equalTo("iphone_15"))
            .body("price", Matchers.equalTo(432))
            .body("quantity", Matchers.equalTo(1));
    }
}
