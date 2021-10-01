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
	private String authorityIdentifier;
	@ManyToMany(mappedBy="authorities")
	private List<Role> roles;
	
	public Authority()
	{
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
}
