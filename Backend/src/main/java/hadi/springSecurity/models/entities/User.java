package hadi.springSecurity.models.entities;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import hadi.springSecurity.models.embeddables.Credential;
import hadi.springSecurity.models.embeddables.Name;

@Entity
@Table(name = "Users")
@JsonIgnoreProperties({"credentials"})
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long id;
	@Column(unique=true)
	private String username;
	private String email;
	@Embedded
	private Name name;
	@Embedded
	private Credential credentials;
	
	private boolean isEnabled;
	private boolean isLocked;
	private Instant creationDate;
	private Instant lastAccessDate;
	
	public User() {	}

	public User(String username, String email, Name name, Credential credentials)
	{
		super();
		this.username = username;
		this.email = email;
		this.name = name;
		this.credentials = credentials;
		this.creationDate = Instant.now();
		this.isLocked = false;
		this.isEnabled = true;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Name getName()
	{
		return name;
	}

	public void setName(Name name)
	{
		this.name = name;
	}

	public Credential getCredentials()
	{
		return credentials;
	}

	public void setCredentials(Credential credentials)
	{
		this.credentials = credentials;
	}

	public long getId()
	{
		return id;
	}

	public boolean isEnabled()
	{
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled)
	{
		this.isEnabled = isEnabled;
	}

	public boolean isLocked()
	{
		return isLocked;
	}

	public void setLocked(boolean isLocked)
	{
		this.isLocked = isLocked;
	}

	public Instant getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate(Instant creationDate)
	{
		this.creationDate = creationDate;
	}

	public Instant getLastAccessDate()
	{
		return lastAccessDate;
	}

	public void setLastAccessDate(Instant lastAccessDate)
	{
		this.lastAccessDate = lastAccessDate;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null)
		{
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (name == null)
		{
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (username == null)
		{
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", name=" + name.toString() + "]";
	}
	
	
}
