package hadi.springSecurity.models.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="authorities")
public class Authority
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "authority_id")
	private Long id;
	private String authorityName;
	@Column(unique = true)
	private String authorityIdentifier;
	@ManyToMany(mappedBy="authorities")
	private List<Role> roles;
	
	public Authority()
	{
	}
	
	public Authority(String authorityName)
	{
		super();
		this.authorityName = authorityName;
		this.authorityIdentifier = authorityName.toUpperCase();
	}

	public Authority(Long id, String authorityName, List<Role> roles)
	{
		super();
		this.id = id;
		this.authorityName = authorityName;
		this.roles = roles;
		this.authorityIdentifier = authorityName.toUpperCase();
	}

	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public String getAuthorityName()
	{
		return authorityName;
	}
	
	public void setAuthorityName(String authorityName)
	{
		this.authorityName = authorityName;
	}

	public List<Role> getRoles()
	{
		return roles;
	}

	public void setRoles(List<Role> roles)
	{
		this.roles = roles;
	}

	public String getAuthorityIdentifier()
	{
		return authorityIdentifier;
	}

	public void setAuthorityIdentifier(String authorityIdentifier)
	{
		this.authorityIdentifier = authorityIdentifier;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorityIdentifier == null) ? 0 : authorityIdentifier.hashCode());
		result = prime * result + ((authorityName == null) ? 0 : authorityName.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
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
		Authority other = (Authority) obj;
		if (authorityIdentifier == null)
		{
			if (other.authorityIdentifier != null)
				return false;
		} else if (!authorityIdentifier.equals(other.authorityIdentifier))
			return false;
		if (authorityName == null)
		{
			if (other.authorityName != null)
				return false;
		} else if (!authorityName.equals(other.authorityName))
			return false;
		if (roles == null)
		{
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		return true;
	}
	
	
}
