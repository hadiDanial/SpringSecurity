package hadi.springSecurity.security.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import hadi.springSecurity.configuration.Properties;
import hadi.springSecurity.models.entities.User;
import hadi.springSecurity.models.responses.AuthenticationResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class JwtProvider
{
	private final Properties properties;
	
	@Autowired
	public JwtProvider(Properties properties)
	{
		super();
		this.properties = properties;
	}

	public String generateToken(String username, Date date)
	{
		return Jwts.builder()
			.setSubject(username)
			.setExpiration(date)
			.signWith(SignatureAlgorithm.HS512, properties.getSecret())
			.compact();
	}

	public AuthenticationResponse generateAuthResponse(User user)
	{
		AuthenticationResponse response = new AuthenticationResponse();
		Instant tokenExpiration = Instant.now().plus(properties.getTokenLifetime(), ChronoUnit.MINUTES);
		Instant refreshTokenExpiration = Instant.now().plus(properties.getRefreshTokenLifetime(), ChronoUnit.MINUTES);
		Date tokenExpirationDate = Date.from(tokenExpiration);
		Date refreshTokenExpirationDate = Date.from(refreshTokenExpiration);
		response.setAuthenticationToken(generateToken(user.getUsername(), tokenExpirationDate));
		response.setRefreshToken(generateToken(user.getUsername(), refreshTokenExpirationDate));
		response.setExpiresAt(tokenExpiration);
		response.setUsername(user.getUsername());
		return response;
	}


}
