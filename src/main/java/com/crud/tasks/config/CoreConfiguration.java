package com.crud.tasks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CoreConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

/*
    // GET request
    getForObject(String url, Class responseType, Map uriVariables)

    // POST request
    postForObject(String url, Object request, Class responseType,Object... uriVariables)

    // PUT request
    put(URI url, Object request)

    // DELETE request
    delete(String url, Map uriVariables)
    */
}
