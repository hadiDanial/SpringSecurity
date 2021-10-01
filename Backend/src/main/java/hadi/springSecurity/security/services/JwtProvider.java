package hadi.springSecurity.security.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hadi.springSecurity.configuration.Properties;
import hadi.springSecurity.exceptions.TokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
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
			.setIssuedAt(Date.from(Instant.now()))
			.setIssuer(properties.getJwtIssuer())
			.signWith(SignatureAlgorithm.HS512, properties.getJwtSecret())
			.compact();
	}

	public Date generateTokenExpirationDate(int lifetime)
	{
		Instant tokenExpiration = Instant.now().plus(lifetime, ChronoUnit.MINUTES);
		return Date.from(tokenExpiration);
	}

	public String getUsernameFromToken(String token)
	{
		Jws<Claims> jws;
		try {
			jws = Jwts.parser().setSigningKey(properties.getJwtSecret())
					.parseClaimsJws(token);
//			System.out.println("JWS Subject: " + jws.getBody().getSubject());
//			System.out.println("Issued by: " + jws.getBody().getIssuer());
//			System.out.println("Issued at: " + jws.getBody().getIssuedAt());
//			System.out.println("Expires: " + jws.getBody().getExpiration());
			return jws.getBody().getSubject();
		}
		catch(JwtException e)
		{
			throw new JwtException("Can't parse JWT (getUsernameFromToken)");
		}
	}
	
	public boolean isTokenValid(String token)
	{
		if(token == null || token.isEmpty()) {
			throw new TokenException("Token is empty or null.");
		}
		Jws<Claims> jws;
		try {
			jws = Jwts.parser().setSigningKey(properties.getJwtSecret())
					.parseClaimsJws(token);
			
			return jws.getBody().getIssuer().equals(properties.getJwtIssuer()) 
					&&	Instant.now().isBefore(jws.getBody().getExpiration().toInstant());
		}
		catch(JwtException e)
		{
			throw new JwtException("Invalid token, can't parse JWT.");
		}
	}
}
