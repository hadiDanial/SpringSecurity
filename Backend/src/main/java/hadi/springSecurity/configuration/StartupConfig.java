package hadi.springSecurity.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import hadi.springSecurity.models.embeddables.Name;
import hadi.springSecurity.models.entities.User;
import hadi.springSecurity.services.RoleService;
import hadi.springSecurity.services.UserService;

@Configuration
@Component
public class StartupConfig implements ApplicationListener<ContextRefreshedEvent>
{
	private final UserService userService;
	private final RoleService roleService;

	@Autowired
	public StartupConfig(UserService userService, RoleService roleService)
	{
		super();
		this.userService = userService;
		this.roleService = roleService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event)
	{
		generateMainRoles();
		generateDummyUsers();
	}

	private void generateMainRoles()
	{
		roleService.addNewRole("Admin");
		roleService.addNewRole("Manager");
		roleService.addNewRole("User");
		roleService.addNewAuthority("Create");
		roleService.addNewAuthority("Read");
		roleService.addNewAuthority("Update");
		roleService.addNewAuthority("Delete");
		List<String> authorities = new ArrayList<>();
		authorities.add("Create");
		authorities.add("Read");
		authorities.add("Update");
		authorities.add("Delete");
		roleService.addAuthoritiesToRole(authorities, "Admin");
		authorities.remove("Delete");
		roleService.addAuthoritiesToRole(authorities, "Manager");
		authorities.remove("Create");
		roleService.addAuthoritiesToRole(authorities, "User");
	}

	private void generateDummyUsers()
	{
		try
		{
			userService.findUserByUsername("John");
		} catch (UsernameNotFoundException e)
		{
			userService.createNewUser("John", "hadi.dan@me.com", "Magical", new Name("John", "Constantine"));
			User john = userService.findUserByUsername("John");
			userService.addRoleToUser(john, "Admin");
		}
		try
		{
			userService.findUserByUsername("Bill");
		} catch (UsernameNotFoundException e)
		{
			userService.createNewUser("Bill", "bill@gmail.com", "12345", new Name("Bill", "The", "Drill"));
			User bill =userService.findUserByUsername("Bill");
			userService.addRoleToUser(bill, "User");
		}
	}

}
