package hadi.springSecurity.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import hadi.springSecurity.security.filters.UsernamePasswordAuthenticationFilter;

public class UsernamePasswordAuthenticationFilterBean
{
	@Autowired
	private AuthenticationManager authManager;
	

	@Bean
	public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter()
	{
		return new UsernamePasswordAuthenticationFilter(authManager);
	}
}
