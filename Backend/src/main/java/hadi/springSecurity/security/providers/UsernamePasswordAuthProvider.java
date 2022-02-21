package hadi.springSecurity.security.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import hadi.springSecurity.models.entities.User;
import hadi.springSecurity.models.requests.LoginRequest;
import hadi.springSecurity.models.security.SecurityUser;
import hadi.springSecurity.security.authentications.UsernamePasswordAuthentication;
import hadi.springSecurity.services.AuthenticationService;

public class UsernamePasswordAuthProvider implements AuthenticationProvider
{
	private final AuthenticationService authenticationService;
	
	@Autowired
	public UsernamePasswordAuthProvider(AuthenticationService authenticationService)
	{
		super();
		this.authenticationService = authenticationService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{
		var username = authentication.getName().toString();
		var password = (String) authentication.getCredentials();
		try
		{
			User user = authenticationService.isValidUser(new LoginRequest(username, password));
			SecurityUser securityUser = new SecurityUser(user);
			if(!securityUser.isAccountNonLocked())
			{
				throw new LockedException(user.getUsername() + " is locked.");
			}
			if(!securityUser.isEnabled())
			{
				throw new DisabledException(user.getUsername() + " is disabled.");
			}
			Authentication auth = new UsernamePasswordAuthentication(securityUser.getUsername(),securityUser.getPassword(), securityUser.getAuthorities());
			return auth;
		} 
		catch (Exception e)
		{
			throw new BadCredentialsException("Wrong credentials.");
		}
	}

	@Override
	public boolean supports(Class<?> authentication)
	{
		return UsernamePasswordAuthentication.class.equals(authentication);
	}

}
