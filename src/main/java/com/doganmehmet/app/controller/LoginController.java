package com.doganmehmet.app.controller;

import com.doganmehmet.app.dto.request.LoginRequest;
import com.doganmehmet.app.dto.response.LoginDTO;
import com.doganmehmet.app.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/login")
public class LoginController {
    private final LoginService m_loginService;

    @PostMapping
    public ResponseEntity<LoginDTO> save(@Valid @RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(m_loginService.login(request));
    }
}
