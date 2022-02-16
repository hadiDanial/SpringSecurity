package hadi.springSecurity.models.entities;

import java.time.Instant;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Unverified_Users")
public class UnverifiedUser
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="verification_id")
	private int id;
	private String username;
	private String verificationUUID;
	private Instant creationTime;
	public UnverifiedUser(String username)
	{
		this.username = username;
		verificationUUID = UUID.randomUUID().toString();
		creationTime = Instant.now();
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getVerificationUUID()
	{
		return verificationUUID;
	}

	public Instant getCreationTime()
	{
		return creationTime;
	}
	
}
