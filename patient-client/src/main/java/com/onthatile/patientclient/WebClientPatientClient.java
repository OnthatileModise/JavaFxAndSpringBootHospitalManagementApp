package com.onthatile.patientclient;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Log4j2
@Component
public class WebClientPatientClient {
    private final WebClient webClient;

    public WebClientPatientClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<StockPrice> pricesFor(String symbol) {
        return webClient.get()
                .uri("http://localhost:8080/stocks/{symbol}" , symbol)
                .retrieve()
                .bodyToFlux(StockPrice.class)
                .retry()
                .doOnError(IOException.class , e -> log.error(e.getMessage()));
    }
}
