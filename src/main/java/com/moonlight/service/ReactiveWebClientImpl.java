package com.moonlight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@Service
public class ReactiveWebClientImpl implements ReactiveWebClient{

    @Autowired
    private WebClient webClient;
    @Override
    public <T, S> Flux<T> postFlux(String url, S body, Class<T> clasz, HttpHeaders httpHeaders) {
        return webClient.post()
                .uri(URI.create(url))
                .bodyValue(body)
                .headers(h-> h.addAll(httpHeaders))
                .retrieve()
                .bodyToFlux(clasz);
    }

    @Override
    public <T, S> Mono<T> postMono(String url, S body, Class<T> tClass, HttpHeaders headers) {
        return webClient.post()
                .uri(URI.create(url))
                .bodyValue(body)
                .headers(header -> header.addAll(headers))
                .retrieve()
                .bodyToMono(tClass);
    }

    @Override
    public <T, S> Flux<T> getFlux(String url, Class<T> tClass, HttpHeaders headers) {
        return webClient.get()
                .uri(URI.create(url))
                .headers(h-> h.addAll(headers))
                .retrieve()
                .bodyToFlux(tClass);
    }

    @Override
    public <T, S> Mono<T> getMono(String url, Class<T> tClass, HttpHeaders headers) {
        return webClient.get()
                .uri(URI.create(url))
                .headers(h -> h.addAll(headers))
                .retrieve()
                .bodyToMono(tClass);
    }

    @Override
    public <T> Mono<ResponseEntity<T>> getMonoEntity(String url, Class<T> clasz, HttpHeaders httpHeaders){
        return webClient.get()
                .uri(URI.create(url))
                .headers(h->h.addAll(httpHeaders))
                .retrieve()
                .toEntity(clasz)
                .log();
    }

    @Override
    public <T, S> Mono<S> putMono(String url, T body, Class<S> clasz, HttpHeaders httpHeaders){
        return webClient.put()
                .uri(URI.create(url))
                .bodyValue(body)
                .headers(h->h.addAll(httpHeaders))
                .retrieve()
                .bodyToMono(clasz)
                .log();
    }

    @Override
    public <T, S> Flux<S> putFlux(String url, T body, Class<S> clasz, HttpHeaders httpHeaders){
        return webClient.put()
                .uri(URI.create(url))
                .bodyValue(body)
                .headers(h->h.addAll(httpHeaders))
                .retrieve()
                .bodyToFlux(clasz)
                .log();
    }
}
