package hadi.springSecurity.security.authentications;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UsernamePasswordAuthentication extends UsernamePasswordAuthenticationToken
{
	private static final long serialVersionUID = -4363412539437295590L;

	public UsernamePasswordAuthentication(Object principal, Object credentials)
	{
		super(principal, credentials);
	}

	public UsernamePasswordAuthentication(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities)
	{
		super(principal, credentials, authorities);
	}

}
