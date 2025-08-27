package com.doganmehmet.app.service;

import com.doganmehmet.app.dto.request.LoginRequest;
import com.doganmehmet.app.dto.response.LoginDTO;
import com.doganmehmet.app.entity.JwtToken;
import com.doganmehmet.app.entity.User;
import com.doganmehmet.app.jwt.JWTUtil;
import com.doganmehmet.app.mapper.IJwtTokenMapper;
import com.doganmehmet.app.repository.IJwtTokenRepository;
import com.doganmehmet.app.repository.IRefreshTokenRepository;
import com.doganmehmet.app.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final IUserRepository m_userRepository;
    private final JWTUtil m_jwtUtil;
    private final AuthenticationManager m_authenticationManager;
    private final IJwtTokenRepository m_jwtTokenRepository;
    private final IRefreshTokenRepository m_refreshTokenRepository;
    private final IJwtTokenMapper m_jwtTokenMapper;


    private void clearOldTokens(User user)
    {
        user.setRefreshToken(null);
        user.setJwtToken(null);
        m_userRepository.save(user);
    }

    private LoginDTO generateTokens(User user)
    {
        var token = m_jwtTokenRepository.findByUser(user);

        if (token.isPresent()) {
            if (!m_jwtUtil.isTokenExpired(token.get().getToken()))
                return m_jwtTokenMapper.toDto(token.get());

            clearOldTokens(user);
        }

        var newToken = new JwtToken();
        var refreshToken = m_jwtUtil.generateRefreshToken(user);
        user.setRefreshToken(refreshToken);
        m_refreshTokenRepository.save(refreshToken);

        newToken.setUser(user);
        newToken.setToken(m_jwtUtil.generateToken(user));

        return m_jwtTokenMapper.toDto(m_jwtTokenRepository.save(newToken));

    }

    public LoginDTO login(LoginRequest request)
    {
        var user = m_userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        try {
            var authToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            m_authenticationManager.authenticate(authToken);
        }
        catch (Exception e) {
            throw new BadCredentialsException("Invalid password");
        }

        return generateTokens(user);
    }
}
