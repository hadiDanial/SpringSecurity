package hadi.springSecurity.models.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Long id;
	private String roleName;
	@Column(unique = true)
	private String roleIdentifier;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	  name = "roles_authorities", 
	  joinColumns = @JoinColumn(name = "role_id"), 
	  inverseJoinColumns = @JoinColumn(name = "authority_id"))
	private List<Authority> authorities;

	@ManyToMany(mappedBy="roles")	
	private List<User> users;
	
	public Role()
	{
	}

	public Role(String roleName)
	{
		super();
		this.roleName = roleName;
		this.roleIdentifier = roleName.toUpperCase();
	}
	
	public Role(Long id, String roleName, List<Authority> authorities)
	{
		super();
		this.id = id;
		this.roleName = roleName;
		this.authorities = authorities;
		this.roleIdentifier = roleName.toUpperCase();
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	public List<Authority> getAuthorities()
	{
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities)
	{
		this.authorities = authorities;
	}

	public String getRoleIdentifier()
	{
		return roleIdentifier;
	}

	public void setRoleIdentifier(String roleIdentifier)
	{
		this.roleIdentifier = roleIdentifier;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((roleIdentifier == null) ? 0 : roleIdentifier.hashCode());
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
		Role other = (Role) obj;
		if (id == null)
		{
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (roleIdentifier == null)
		{
			if (other.roleIdentifier != null)
				return false;
		} else if (!roleIdentifier.equals(other.roleIdentifier))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Role [id=" + id + ", roleName=" + roleName + ", roleIdentifier=" + roleIdentifier + "]";
	}
	
	
}
