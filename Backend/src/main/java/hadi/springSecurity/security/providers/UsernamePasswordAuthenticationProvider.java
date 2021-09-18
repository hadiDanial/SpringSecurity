package hadi.springSecurity.security.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import hadi.springSecurity.BL.UserBL;
import hadi.springSecurity.beans.entities.User;
import hadi.springSecurity.security.authentications.UsernamePasswordAuthentication;
import hadi.springSecurity.security.model.SecurityUser;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider
{
	private final UserBL userBL;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UsernamePasswordAuthenticationProvider(UserBL userBL, PasswordEncoder passwordEncoder)
	{
		this.userBL = userBL;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		User user = userBL.findUserByUsername(username);
		if (user != null)
		{
//			throw new BadCredentialsException(":(");			
			SecurityUser securityUser = new SecurityUser(user);
			if (passwordEncoder.matches(password, securityUser.getPassword()))
			{
				return new UsernamePasswordAuthentication(username, password, securityUser.getAuthorities());
			}
		}
		return new UsernamePasswordAuthentication("NONE", "", null);

//		throw new BadCredentialsException(":(");
	}

	@Override
	public boolean supports(Class<?> authentication)
	{
		// TODO Auto-generated method stub
		return UsernamePasswordAuthentication.class.equals(authentication);
	}

}
