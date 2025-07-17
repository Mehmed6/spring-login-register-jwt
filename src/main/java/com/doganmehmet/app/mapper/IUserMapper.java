package com.doganmehmet.app.mapper;

import com.doganmehmet.app.dto.request.RegisterRequest;
import com.doganmehmet.app.dto.response.RegisterDTO;
import com.doganmehmet.app.entity.User;
import org.mapstruct.Mapper;

@Mapper(implementationName = "UserMapperImpl", componentModel = "spring")
public interface IUserMapper {

    RegisterDTO toUserDTO(User user);
    User toUser(RegisterRequest registerRequest);
}
