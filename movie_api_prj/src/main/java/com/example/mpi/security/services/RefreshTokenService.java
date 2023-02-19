package com.example.mpi.security.services;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mpi.entity.RefreshToken;
import com.example.mpi.mapper.RefreshTokenMapper;
import com.example.mpi.mapper.UserMapper;
import com.example.mpi.security.jwt.exception.TokenRefreshException;

@Service
public class RefreshTokenService {
	
	@Value("${myweb.mpi.jwtRefreshExpirationMs}")
	private Long refreshTokenDurationMs;
	
	@Autowired
	private RefreshTokenMapper refreshTokenRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	public Optional<RefreshToken> findByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}
	
	public RefreshToken createRefreshToken(String userId) {
		
		RefreshToken refreshToken = RefreshToken.builder()
												.userId(userMapper.findByUserId(userId).getUserId())
												.token(UUID.randomUUID().toString())
												.expiryDate(Instant.now().plusMillis(refreshTokenDurationMs)).build();
		
		refreshTokenRepository.save(refreshToken);
		
		String insertAfterUserId = refreshToken.getUserId();
		
		refreshToken = refreshTokenRepository.findById(insertAfterUserId);
		
		return refreshToken;
	}
	
	public RefreshToken verifyExpiration(RefreshToken token) {
		if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.deleteByToken(token);
			throw new TokenRefreshException(token.getToken(), "리프레시 토큰이 만료되었습니다. 다시 로그인 해주세요.");
		}
		
		return token;
	}
	
	@Transactional
	public int deleteByUserId(String userId) {
		return refreshTokenRepository.deleteByUser(userMapper.findByUserId(userId));
	}
}
