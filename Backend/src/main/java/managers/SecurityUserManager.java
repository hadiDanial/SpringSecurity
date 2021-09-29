package managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import hadi.springSecurity.models.security.SecurityUser;
import hadi.springSecurity.services.UserService;

@Component
public class SecurityUserManager implements UserDetailsManager
{
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public SecurityUserManager(UserService userService, PasswordEncoder passwordEncoder)
	{
		super();
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		SecurityUser user = new SecurityUser(userService.findUserByUsername(username));
		return user;
	}

	@Override
	public void createUser(UserDetails user)
	{
		userService.createUser(user.getUsername(), "", user.getPassword(), "", "", "");
	}

	@Override
	public void updateUser(UserDetails user)
	{
//		String username = user.getUsername();
//		String password = user.getPassword();
//		user.isAccountNonExpired()
//		userBL.updateUser

	}

	@Override
	public void deleteUser(String username)
	{
		userService.deleteUser(username);
	}

	@Override
	public void changePassword(String oldPassword, String newPassword)
	{
//		User user = getCurrentUserSomehow();
//		if (passwordEncoder.matches(oldPassword, user.getCredentials().getPassword()))
//		{
//			userBL.updatePassword(user, newPassword);
//		}

	}

	@Override
	public boolean userExists(String username)
	{
		return userService.userExists(username);
	}

}
