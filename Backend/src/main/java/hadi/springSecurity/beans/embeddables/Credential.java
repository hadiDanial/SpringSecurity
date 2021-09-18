package hadi.springSecurity.beans.embeddables;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;

@Embeddable
public class Credential
{
	private String password;
	private LocalDateTime credentialExpirationDate;
	
	public Credential(String password, LocalDateTime credentialExpirationDate)
	{
		super();
		this.password = password;
		this.credentialExpirationDate = credentialExpirationDate;
	}
	
	public boolean isNonExpired()
	{
		return !LocalDateTime.now().isAfter(credentialExpirationDate);
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public LocalDateTime getCredentialExpirationDate()
	{
		return credentialExpirationDate;
	}
	
	public void setCredentialExpirationDate(LocalDateTime credentialExpirationDate)
	{
		this.credentialExpirationDate = credentialExpirationDate;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((credentialExpirationDate == null) ? 0 : credentialExpirationDate.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		Credential other = (Credential) obj;
		if (credentialExpirationDate == null)
		{
			if (other.credentialExpirationDate != null)
				return false;
		} else if (!credentialExpirationDate.equals(other.credentialExpirationDate))
			return false;
		if (password == null)
		{
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	
	
}
