package hadi.springSecurity.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import hadi.springSecurity.services.UserService;

@Configuration
@Component
public class StartupConfig implements ApplicationListener<ContextRefreshedEvent>
{
	private final UserService userService;

	@Autowired
	public StartupConfig(UserService userService)
	{
		super();
		this.userService = userService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event)
	{
		generateDummyUsers();
		
	}

	private void generateDummyUsers()
	{
		try
		{
			userService.findUserByUsername("John");
		} catch (UsernameNotFoundException e)
		{
			userService.createNewUser("John", "johnnyboy@gmail.com", "MagicalDaddy69", "John", null, "Constantine");
		}
		try
		{
			userService.findUserByUsername("Bill");
		} catch (UsernameNotFoundException e)
		{
			userService.createNewUser("Bill", "bill@gmail.com", "12345", "Bill", "The", "Drill");
		}
	}

}
