package com.doganmehmet.app.controller;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/external")
public class ExternalApiController {
    private final RestTemplate m_restTemplate;

    public ExternalApiController(RestTemplateBuilder builder)
    {
        m_restTemplate = builder.build();
    }

    @GetMapping("/data")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getExternalData()
    {
        var url = "https://api.themoviedb.org/3/movie/upcoming?api_key=" +
                "dfa5cdebbb09ba706e87349e830d63a8&page=1&language=tr-TR";

        var response = m_restTemplate.getForEntity(url, String.class);
        return ResponseEntity.ok(response.getBody());
    }
}
