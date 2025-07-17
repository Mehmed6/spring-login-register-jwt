package com.doganmehmet.app.controller;

import com.doganmehmet.app.dto.request.RefreshTokenRequest;
import com.doganmehmet.app.dto.response.LoginDTO;
import com.doganmehmet.app.service.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/refresh-token")
public class RefreshTokenController {
    private final RefreshTokenService m_refreshTokenService;

    @PostMapping
    public ResponseEntity<LoginDTO> save(@Valid @RequestBody RefreshTokenRequest request)
    {
        return ResponseEntity.ok(m_refreshTokenService.refreshToken(request));
    }
}
