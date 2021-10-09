package hadi.springSecurity.models.entities;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tokens")
public class Token
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	
	private String accessToken;
	private String refreshToken;
	private String username;
	private Instant expiresAt;
	
	public Token()
	{
		super();
	}

	public Token(Long id, String accessToken, String refreshToken, String username, Instant expiresAt)
	{
		super();
		this.id = id;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.username = username;
		this.expiresAt = expiresAt;
	}

	public String getAccessToken()
	{
		return accessToken;
	}

	public void setAccessToken(String accessToken)
	{
		this.accessToken = accessToken;
	}

	public String getRefreshToken()
	{
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken)
	{
		this.refreshToken = refreshToken;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public Instant getExpiresAt()
	{
		return expiresAt;
	}

	public void setExpiresAt(Instant expiresAt)
	{
		this.expiresAt = expiresAt;
	}

	public Long getId()
	{
		return id;
	}
}
