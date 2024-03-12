package com.onthatile.patientclient;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public WebClientPatientClient webClientPatientClient(WebClient webClient){
        return new WebClientPatientClient(webClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public WebClient webClient(){
        return WebClient.builder().build();
    }
}
