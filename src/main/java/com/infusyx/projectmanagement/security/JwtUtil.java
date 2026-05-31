package com.infusyx.projectmanagement.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private static final String SECRET_KEY = "mySuperSecretKeyForJwtAuthenticationProjectManagementSystem123";

	private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

	private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

	public String generateToken(String email) {

		return Jwts.builder().subject(email).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(key).compact();
	}

	public String extractUsername(String token) {

		return extractAllClaims(token).getSubject();
	}

	public boolean isTokenValid(String token, String email) {

		String username = extractUsername(token);

		return username.equals(email) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {

		return extractAllClaims(token).getExpiration().before(new Date());
	}

	private Claims extractAllClaims(String token) {

		return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
	}
}