package com.doganmehmet.app.mapper;

import com.doganmehmet.app.dto.response.LoginDTO;
import com.doganmehmet.app.entity.JwtToken;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(implementationName = "JwtTokenMapperImpl", componentModel = "spring")
public interface IJwtTokenMapper {

    @Mapping(target = "username", source = "jwtToken.user.username")
    @Mapping(target = "jwtToken", source = "token")
    @Mapping(target = "refreshToken", source = "jwtToken.user.refreshToken.refreshToken")
    LoginDTO toDto(JwtToken jwtToken);
}
