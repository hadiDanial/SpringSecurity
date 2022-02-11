package hadi.springSecurity.models.entities;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tokens")
@JsonIgnoreProperties({"id", "createdAt", "updatedAt"})
public class Token
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	
	private String accessToken;
	private String refreshToken;
	private String username;
	private Instant createdAt;
	private Instant updatedAt;
	private Instant expiresAt;
	
	public Token()
	{
		super();
		this.createdAt = Instant.now();
	}

	public Token(Long id, String accessToken, String refreshToken, String username, Instant expiresAt)
	{
		super();
		this.id = id;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.username = username;
		this.expiresAt = expiresAt;
		this.createdAt = Instant.now();
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
	
	public Instant getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt)
	{
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt()
	{
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt)
	{
		this.updatedAt = updatedAt;
	}

	public Long getId()
	{
		return id;
	}
}
