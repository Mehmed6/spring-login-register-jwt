package com.doganmehmet.app.controller;

import com.doganmehmet.app.dto.request.RegisterRequest;
import com.doganmehmet.app.dto.response.RegisterDTO;
import com.doganmehmet.app.service.RegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/register")
public class RegisterController {
    private final RegisterService m_registerService;

    @PostMapping
    public ResponseEntity<RegisterDTO> save(@Valid @RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(m_registerService.save(request));
    }
}
