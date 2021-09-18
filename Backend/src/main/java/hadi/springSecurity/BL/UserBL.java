package hadi.springSecurity.BL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hadi.springSecurity.configuration.Properties;

@Service
public class UserBL
{
	private final Properties properties;

	@Autowired
	public UserBL(Properties properties)
	{
		super();
		this.properties = properties;
	}
	
	
}
