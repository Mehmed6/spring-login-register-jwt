package com.doganmehmet.app.service;

import com.doganmehmet.app.dto.request.RefreshTokenRequest;
import com.doganmehmet.app.dto.response.LoginDTO;
import com.doganmehmet.app.entity.JwtToken;
import com.doganmehmet.app.entity.User;
import com.doganmehmet.app.jwt.JWTUtil;
import com.doganmehmet.app.mapper.IJwtTokenMapper;
import com.doganmehmet.app.repository.IJwtTokenRepository;
import com.doganmehmet.app.repository.IRefreshTokenRepository;
import com.doganmehmet.app.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final IRefreshTokenRepository m_refreshTokenRepository;
    private final IUserRepository m_userRepository;
    private final IJwtTokenRepository m_jwtTokenRepository;
    private final JWTUtil m_jwtUtil;
    private final IJwtTokenMapper m_jwtTokenMapper;

    private void clearOldTokens(User user)
    {
        user.setRefreshToken(null);
        user.setJwtToken(null);
        m_userRepository.save(user);
    }

    public LoginDTO refreshToken(RefreshTokenRequest request)
    {
        var refreshToken = m_refreshTokenRepository.findByRefreshToken(request.getRefreshToken())
                .orElseThrow(() -> new RuntimeException("Refresh refreshToken not found"));

        var user = refreshToken.getUser();

        if (refreshToken.getExpiresAt().before(new Date()))
            throw new RuntimeException("Refresh refreshToken expired");

        clearOldTokens(user);

        var newJwtToken = new JwtToken();
        var newRefreshToken = m_jwtUtil.generateRefreshToken(user);

        user.setRefreshToken(newRefreshToken);
        m_refreshTokenRepository.save(newRefreshToken);
        m_userRepository.save(user);
        newJwtToken.setUser(user);
        newJwtToken.setToken(m_jwtUtil.generateToken(user));

        return m_jwtTokenMapper.toDto(m_jwtTokenRepository.save(newJwtToken));

    }
}
