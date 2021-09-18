package hadi.springSecurity.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@PropertySource("classpath:application.properties")
@Component
public class Properties //implements ApplicationListener<ContextRefreshedEvent> 
{
	@Value("${credentials.expires-in}")
	private int credentialExpirationDays;

	public int getCredentialExpirationDays()
	{
		return credentialExpirationDays;
	}
	
}
