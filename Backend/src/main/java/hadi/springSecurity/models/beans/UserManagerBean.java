package hadi.springSecurity.models.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import hadi.springSecurity.services.UserService;

@Configuration
public class UserManagerBean
{
	private final UserService userService;
	
	@Autowired
	public UserManagerBean(UserService userService)
	{
		super();
		this.userService = userService;
	}


	@Bean
	public UserDetailsService securityUserManager()
	{
		return userService;
	}
}
