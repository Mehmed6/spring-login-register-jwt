package com.doganmehmet.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "refresh_tokens")
public class RefreshToken extends BaseEntity{

    @Column(unique = true, nullable = false)
    private String refreshToken;

    @Column(name = "expires_at", nullable = false)
    private Date expiresAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
