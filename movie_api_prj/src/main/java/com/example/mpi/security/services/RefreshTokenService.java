package com.example.mpi.security.services;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mpi.entity.RefreshToken;
import com.example.mpi.repository.RefreshTokenRepository;
import com.example.mpi.repository.UserRepository;
import com.example.mpi.security.jwt.exception.TokenRefreshException;

@Service
public class RefreshTokenService {
	
	@Value("${myweb.mpi.jwtRefreshExpirationMs}")
	private Long refreshTokenDurationMs;
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Optional<RefreshToken> findByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}
	
	public RefreshToken createRefreshToken(String userId) {
		
		RefreshToken refreshToken = RefreshToken.builder()
												.user(userRepository.findByUserId(userId).get())
												.token(UUID.randomUUID().toString())
												.expiryDate(Instant.now().plusMillis(refreshTokenDurationMs)).build();
		
		String insertAfterUserId = refreshTokenRepository.save(refreshToken);
		
		refreshToken = refreshTokenRepository.findById(insertAfterUserId);
		
		return refreshToken;
	}
	
	public RefreshToken verifyExpiration(RefreshToken token) throws TokenRefreshException {
		if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.deleteByToken(token);
			throw new TokenRefreshException(token.getToken(), "리프레시 토큰이 만료되었습니다. 다시 로그인 해주세요.");
		}
		
		return token;
	}
	
	@Transactional
	public int deleteByUserId(String userId) {
		return refreshTokenRepository.deleteByUser(userRepository.findByUserId(userId).get());
	}
}
