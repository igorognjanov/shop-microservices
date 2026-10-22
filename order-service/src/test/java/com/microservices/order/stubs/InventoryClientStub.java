package com.microservices.order.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class InventoryClientStub {

    public static void stubCallForInventory(String code, int quantity, boolean successful) {
        stubFor(get(urlEqualTo("/api/inventory?code=" + code + "&quantity=" + quantity))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(String.valueOf(successful))));
    }
}
