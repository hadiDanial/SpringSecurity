package hadi.springSecurity.models.entities;

import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import hadi.springSecurity.models.embeddables.Credential;
import hadi.springSecurity.models.embeddables.Name;

@Entity
@Table(name = "Users")
@JsonIgnoreProperties({"credentials", "lastLoginDate", "lastAccessDate", "isEnabled", "isLocked"})
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long id;
	@Column(unique=true)
	@Size(min = 3, max = 20)
	@NotNull
	@NotBlank
	private String username;
	@NotNull
	@Email
	private String email;
	@Embedded
	private Name name;
	@Embedded
	private Credential credentials;
	
	private boolean isEnabled;
	private boolean isLocked;
	private boolean isVerified;
	private Instant creationDate;
	private Instant lastLoginDate;
	private Instant lastAccessDate;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"), 
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private UserProfile profile;
	
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
		this.isVerified = false;
		this.profile = new UserProfile(this);
	}

	public User(long id, String username, String email, Name name, Credential credentials, boolean isEnabled,
			boolean isLocked, Instant creationDate, Instant lastAccessDate, List<Role> roles)
	{
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.name = name;
		this.credentials = credentials;
		this.isEnabled = isEnabled;
		this.isLocked = isLocked;
		this.creationDate = creationDate;
		this.lastLoginDate = lastAccessDate;
		this.roles = roles;
		this.profile = new UserProfile(this);
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

	public boolean isVerified()
	{
		return isVerified;
	}

	public void setVerified(boolean isVerified)
	{
		this.isVerified = isVerified;
	}

	public Instant getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate(Instant creationDate)
	{
		this.creationDate = creationDate;
	}

	public Instant getLastLoginDate()
	{
		return lastLoginDate;
	}

	public void setLastLoginDate(Instant lastAccessDate)
	{
		this.lastLoginDate = lastAccessDate;
	}

	public Instant getLastAccessDate()
	{
		return lastAccessDate;
	}

	public void setLastAccessDate(Instant lastAccessDate)
	{
		this.lastAccessDate = lastAccessDate;
	}

	public List<Role> getRoles()
	{
		return roles;
	}

	public void setRoles(List<Role> roles)
	{
		this.roles = roles;
	}

	public UserProfile getProfile()
	{
		return profile;
	}

	public void setProfile(UserProfile profile)
	{
		this.profile = profile;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
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
		if (creationDate == null)
		{
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (credentials == null)
		{
			if (other.credentials != null)
				return false;
		} else if (!credentials.equals(other.credentials))
			return false;
		if (email == null)
		{
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (isEnabled != other.isEnabled)
			return false;
		if (isLocked != other.isLocked)
			return false;
		if (lastLoginDate == null)
		{
			if (other.lastLoginDate != null)
				return false;
		} else if (!lastLoginDate.equals(other.lastLoginDate))
			return false;
		if (name == null)
		{
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (roles == null)
		{
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
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
