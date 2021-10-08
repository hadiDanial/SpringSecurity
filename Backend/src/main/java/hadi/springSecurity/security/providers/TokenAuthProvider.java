package hadi.springSecurity.security.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import hadi.springSecurity.models.requests.ValidateTokenRequest;
import hadi.springSecurity.models.security.SecurityUser;
import hadi.springSecurity.security.authentications.TokenAuthentication;
import hadi.springSecurity.security.authentications.UsernamePasswordAuthentication;
import hadi.springSecurity.services.AuthenticationService;

public class TokenAuthProvider implements AuthenticationProvider
{
	private final AuthenticationService authenticationService;

	@Autowired
	public TokenAuthProvider(AuthenticationService authenticationService)
	{
		super();
		this.authenticationService = authenticationService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{
		var accessToken = authentication.getName();
//		var refreshToken = (String) authentication.getCredentials();
		try
		{
//			authenticationService.isValidAuthToken(refreshToken);
			if(authenticationService.isValidAuthToken(accessToken))
			{
				SecurityUser securityUser = new SecurityUser(authenticationService.getUserFromToken(accessToken));
				if(!securityUser.isAccountNonLocked())
				{
					throw new LockedException(securityUser.getUsername() + " is locked.");
				}
				if(!securityUser.isEnabled())
				{
					throw new DisabledException(securityUser.getUsername() + " is disabled.");
				}
				Authentication auth = new TokenAuthentication(securityUser.getUsername(),securityUser.getPassword(), securityUser.getAuthorities());
				return auth;				
			}
			else
			{
				throw new BadCredentialsException("Invalid token.");
			}
			
		} catch (Exception e)
		{
			 throw new BadCredentialsException("Invalid token.");
		}
	}

	@Override
	public boolean supports(Class<?> authentication)
	{
		return TokenAuthentication.class.equals(authentication);
	}
}