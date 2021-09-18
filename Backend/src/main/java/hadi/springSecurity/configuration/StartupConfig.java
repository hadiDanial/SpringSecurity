package hadi.springSecurity.configuration;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class StartupConfig implements ApplicationListener<ContextRefreshedEvent> 
{

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event)
	{
		// TODO Auto-generated method stub
		
	}

}
