package com.moonlight.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {
    @Bean
    WebClient createWebclient() {
        WebClient webClient = WebClient.builder()
                .exchangeStrategies(ExchangeStrategies
                        .builder()
                        .codecs(config->config.defaultCodecs().maxInMemorySize(-1))
                        .build())
                .clientConnector(new ReactorClientHttpConnector(HttpClient.newConnection()))
                .build();
        return webClient;
    }
}
