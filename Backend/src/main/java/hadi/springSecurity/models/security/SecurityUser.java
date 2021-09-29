package hadi.springSecurity.models.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import hadi.springSecurity.models.entities.User;

public class SecurityUser implements UserDetails
{
	private final User user;
	
	public SecurityUser(User user)
	{
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		// TODO Auto-generated method stub
		return null;
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

}
