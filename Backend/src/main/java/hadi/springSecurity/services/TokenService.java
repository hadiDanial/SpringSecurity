package hadi.springSecurity.services;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hadi.springSecurity.configuration.Properties;
import hadi.springSecurity.exceptions.TokenException;
import hadi.springSecurity.models.entities.Token;
import hadi.springSecurity.models.entities.User;
import hadi.springSecurity.repositories.TokenRepository;
import hadi.springSecurity.security.services.JwtProvider;
import io.jsonwebtoken.JwtException;

@Service
public class TokenService
{
	private final TokenRepository tokenRepository;
	private final JwtProvider jwtProvider;
	private final Properties properties;

	@Autowired
	public TokenService(JwtProvider jwtProvider, TokenRepository tokenRepository, Properties properties)
	{
		super();
		this.tokenRepository = tokenRepository;
		this.jwtProvider = jwtProvider;
		this.properties = properties;
	}

	public void saveToken(Token token)
	{
		try
		{
			tokenRepository.save(token);

		} catch (Exception e)
		{
			throw new TokenException("Token Save Exception");
		}
	}

	public void deleteToken(Token token)
	{
		try
		{
			Token t = tokenRepository.findById(token.getId()).get();
			tokenRepository.delete(t);
		} catch (Exception e)
		{
			throw new TokenException("Token Delete Exception");
		}
	}

	public Token generateToken(User user)
	{
		clearOutdatedTokensForUser(user);
		Token token = new Token();
		Date tokenExpirationDate = jwtProvider.generateTokenExpirationDate(properties.getTokenLifetime());
		Date refreshTokenExpirationDate = jwtProvider.generateTokenExpirationDate(properties.getRefreshTokenLifetime());
		token.setAccessToken(jwtProvider.generateToken(user.getUsername(), tokenExpirationDate));
		token.setRefreshToken(jwtProvider.generateToken(user.getUsername(), refreshTokenExpirationDate));
		token.setExpiresAt(refreshTokenExpirationDate.toInstant());
		token.setUsername(user.getUsername());
		tokenRepository.save(token);
		return token;
	}

	private void clearOutdatedTokensForUser(User user)
	{
		List<Token> oldTokens = tokenRepository.findAllByUsername(user.getUsername());
		for(Token token : oldTokens)
		{
			if(token.getExpiresAt().isBefore(Instant.now()))
			{
				tokenRepository.delete(token);
			}
		}
	}

	private Token generateNewAccessToken(String refreshToken)
	{
		Token token = findTokenByRefreshToken(refreshToken);
		Date tokenExpirationDate = jwtProvider.generateTokenExpirationDate(properties.getTokenLifetime());
		token.setAccessToken(
				jwtProvider.generateToken(jwtProvider.getUsernameFromToken(refreshToken), tokenExpirationDate));
		tokenRepository.save(token);
		return token;
	}

	private Token findTokenByRefreshToken(String refreshToken)
	{
		Token token = tokenRepository.findByRefreshToken(refreshToken)
				.orElseThrow(() -> new TokenException("Token not found by refresh token"));
		return token;
	}

	private Token findTokenByUsername(String username)
	{
		Token token = tokenRepository.findByUsername(username)
				.orElseThrow(() -> new TokenException("Token not found by username"));
		return token;
	}

	public void deleteToken(String refreshToken)
	{
		Token token = findTokenByRefreshToken(refreshToken);
		tokenRepository.delete(token);
	}

	public boolean isValidAuthToken(String authenticationToken) throws JwtException, TokenException
	{
		return tokenRepository.existsByAccessToken(authenticationToken) && jwtProvider.isTokenValid(authenticationToken);
	}

	public Token updateAccessToken(String refreshToken)
	{
		return generateNewAccessToken(refreshToken);
	}
	
	public String getUsernameFromToken(String token)
	{
		return jwtProvider.getUsernameFromToken(token);
	}

}
