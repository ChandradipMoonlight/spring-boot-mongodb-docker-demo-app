package com.moonlight.utils;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class RestClient {
    private static final RestTemplate restTemplate = new RestTemplate();

    public static JsonNode post(String url, JsonNode request, HttpHeaders headers) {

        HttpEntity<JsonNode> entity = new HttpEntity<>(request, headers);
        log.info("Inside Class[RestClient] Method[post] request : {}", entity);
        return restTemplate.exchange(url, HttpMethod.POST, entity, JsonNode.class).getBody();
    }

    public static String post(String url, String request, HttpHeaders headers) {
        HttpEntity<String> entity = new HttpEntity<>(request, headers);
        return restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
    }
    @PostConstruct
    public void init() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        this.restTemplate.setMessageConverters(messageConverters);
    }
}
