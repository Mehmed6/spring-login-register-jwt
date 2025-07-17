package com.doganmehmet.app.service;

import com.doganmehmet.app.dto.request.RegisterRequest;
import com.doganmehmet.app.dto.response.RegisterDTO;
import com.doganmehmet.app.mapper.IUserMapper;
import com.doganmehmet.app.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final IUserRepository m_userRepository;
    private final IUserMapper m_userMapper;
    private final PasswordEncoder m_passwordEncoder;


    public RegisterDTO save(RegisterRequest request)
    {
        if (m_userRepository.findByUsername(request.getUsername()).isPresent())
            throw new RuntimeException("Username already exists");
        if (m_userRepository.findByEmail(request.getEmail()).isPresent())
            throw new RuntimeException("Email already exists");

        var user = m_userMapper.toUser(request);
        user.setPassword(m_passwordEncoder.encode(request.getPassword()));

        return m_userMapper.toUserDTO(m_userRepository.save(user));
    }
}
