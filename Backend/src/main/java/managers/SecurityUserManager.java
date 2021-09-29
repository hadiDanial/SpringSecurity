package managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import hadi.springSecurity.BL.UserBL;
import hadi.springSecurity.models.security.SecurityUser;

@Component
public class SecurityUserManager implements UserDetailsManager
{
	private final UserBL userBL;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public SecurityUserManager(UserBL userBL, PasswordEncoder passwordEncoder)
	{
		super();
		this.userBL = userBL;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		SecurityUser user = new SecurityUser(userBL.findUserByUsername(username));
		return user;
	}

	@Override
	public void createUser(UserDetails user)
	{
		userBL.createUser(user.getUsername(), "", user.getPassword(), "", "", "");
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
		userBL.deleteUser(username);
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
		return userBL.userExists(username);
	}

}
