package hadi.springSecurity.models.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import hadi.springSecurity.BL.UserBL;
import managers.SecurityUserManager;

@Configuration
public class UserManagerBean
{
	private final UserBL userBL;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserManagerBean(UserBL userBL, PasswordEncoder passwordEncoder)
	{
		super();
		this.userBL = userBL;
		this.passwordEncoder = passwordEncoder;
	}


	@Bean
	public SecurityUserManager securityUserManager()
	{
		return new SecurityUserManager(userBL, passwordEncoder);
	}
}
