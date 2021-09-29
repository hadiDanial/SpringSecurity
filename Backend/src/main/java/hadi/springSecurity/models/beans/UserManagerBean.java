package hadi.springSecurity.models.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import hadi.springSecurity.services.UserService;
import managers.SecurityUserManager;

@Configuration
public class UserManagerBean
{
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserManagerBean(UserService userService, PasswordEncoder passwordEncoder)
	{
		super();
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}


	@Bean
	public SecurityUserManager securityUserManager()
	{
		return new SecurityUserManager(userService, passwordEncoder);
	}
}
