package com.doganmehmet.app.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    private String username;
    private String jwtToken;
    private String refreshToken;
}
