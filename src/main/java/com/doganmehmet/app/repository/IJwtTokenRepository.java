package com.doganmehmet.app.repository;

import com.doganmehmet.app.entity.JwtToken;
import com.doganmehmet.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IJwtTokenRepository extends JpaRepository<JwtToken, Long> {
    Optional<JwtToken> findByUser(User user);
}
