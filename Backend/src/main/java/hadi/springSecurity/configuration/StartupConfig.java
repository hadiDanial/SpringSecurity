package hadi.springSecurity.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import hadi.springSecurity.BL.UserBL;

@Configuration
@Component
public class StartupConfig implements ApplicationListener<ContextRefreshedEvent>
{
	private final UserBL userBL;

	@Autowired
	public StartupConfig(UserBL userBL)
	{
		super();
		this.userBL = userBL;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event)
	{
		if (userBL.findUserByUsername("John") == null)
		{		
			userBL.createUser("John", "johnnyboy@gmail.com", "MagicalDaddy69", "John", null, "Constantine");
		}
		if (userBL.findUserByUsername("Bill") == null)
		{		
			userBL.createUser("Bill", "bill@gmail.com", "12345", "Bill", "The", "Drill");
		}
	}

}
