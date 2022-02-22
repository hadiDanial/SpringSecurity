package hadi.springSecurity.models.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import hadi.springSecurity.models.entities.Authority;
import hadi.springSecurity.models.entities.Role;
import hadi.springSecurity.models.entities.User;

public class SecurityUser implements UserDetails
{
	private static final long serialVersionUID = 440546795580082243L;
	
	private final User user;
	
	public SecurityUser(User user)
	{
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		List<GrantedAuthority> authorities = new ArrayList<>();
		List<Role> roles;
		try
		{
			roles = user.getRoles();
			if(roles == null || roles.size() == 0)
				return null;
			
		} catch (NullPointerException e)
		{
			return null;
		}
		for(Role role : user.getRoles())
		{
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleIdentifier()));
			for(Authority auth : role.getAuthorities())
			{
				authorities.add(new SimpleGrantedAuthority(auth.getAuthorityIdentifier()));				
			}
		}
		return authorities;
	}

	@Override
	public String getPassword()
	{
		return user.getCredentials().getPassword();
	}

	@Override
	public String getUsername()
	{
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return !user.isLocked();
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return user.getCredentials().isNonExpired();
	}

	@Override
	public boolean isEnabled()
	{
		return user.isEnabled();
	}

	public User getUser()
	{
		return user;
	}
	
	

}
