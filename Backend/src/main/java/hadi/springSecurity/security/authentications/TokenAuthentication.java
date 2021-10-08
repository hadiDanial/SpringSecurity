package hadi.springSecurity.security.authentications;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class TokenAuthentication extends UsernamePasswordAuthenticationToken
{
	private static final long serialVersionUID = -4358204487258768294L;

	public TokenAuthentication(Object principal, Object credentials)
	{
		super(principal, credentials);
		
	}

	public TokenAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities)
	{
		super(principal, credentials, authorities);
		
	}

}
