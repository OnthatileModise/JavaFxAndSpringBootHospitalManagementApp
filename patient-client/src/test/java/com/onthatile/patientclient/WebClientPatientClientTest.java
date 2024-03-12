package com.onthatile.patientclient;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

class WebClientPatientClientTest {

//    private final WebClient webClient = WebClient.builder().build();
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void pricesFor() {
//    }
//
//    @Test
//    void shouldRetrievePatientsFromTheService() {
//        //given
//        WebClientPatientClient webClientPatientClient = new WebClientPatientClient(webClient);
//        //when
//        Flux<StockPrice> stockPriceFlux = webClientPatientClient.pricesFor("SYMBOL");
//        //then
//        Assertions.assertNotNull(stockPriceFlux);
//        Flux<StockPrice> fivePrices = stockPriceFlux.take(5);
//        Assertions.assertTrue(stockPriceFlux.take(5).count().block() > 0);
//        Assertions.assertEquals("SYMBOL", fivePrices.blockFirst().getSymbol());
//    }
}