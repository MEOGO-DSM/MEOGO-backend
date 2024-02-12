package com.example.meogobackend.domain.user.domain.repository;

import com.example.meogobackend.domain.user.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    Optional<RefreshToken> findByAccountId(String accountId);
}