package hadi.springSecurity.BL;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hadi.springSecurity.configuration.Properties;
import hadi.springSecurity.models.embeddables.Credential;
import hadi.springSecurity.models.embeddables.Name;
import hadi.springSecurity.models.entities.User;
import hadi.springSecurity.repositories.UserRepository;

@Service
public class UserBL
{
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final Properties properties;

	@Autowired
	public UserBL(UserRepository userRepository, PasswordEncoder passwordEncoder, Properties properties)
	{
		super();
		this.properties = properties;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User createUser(String username, String email, String password, String firstName, String middleName,
			String lastName)
	{
		Name name = generateName(firstName, middleName, lastName);
		Credential credentials = generateCredentials(password);
		User user = new User(username, email, name, credentials);
		try
		{
			userRepository.save(user);
			return findUserByUsername(user.getUsername());
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public boolean updatePassword(String username, String password)
	{
		User user = findUserByUsername(username);
		return updatePassword(user, password);
	}

	public boolean updatePassword(User user, String password)
	{
		if (user == null)
			return false;
		try
		{
			Credential credentials = generateCredentials(password);
			user.setCredentials(credentials);
			userRepository.save(user);
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateName(String username, String firstName, String middleName, String lastName)
	{
		User user = findUserByUsername(username);
		if (user == null)
			return false;
		try
		{
			Name name = generateName(firstName, middleName, lastName);
			user.setName(name);
			userRepository.save(user);
			return true;
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public User findUserByUsername(String username)
	{
		Optional<User> optionalUser = userRepository.findUserByUsername(username);
		User user = optionalUser.orElse(null);
		return user;
	}

	private Credential generateCredentials(String password)
	{
		String hashedPassword = passwordEncoder.encode(password);
		return new Credential(hashedPassword, Instant.now().plus(properties.getCredentialExpirationDays(), ChronoUnit.DAYS));
	}

	private Name generateName(String firstName, String middleName, String lastName)
	{
		Name name;
		if (middleName == null || middleName.equals(""))
		{
			name = new Name(firstName, lastName);
		} else
		{
			name = new Name(firstName, middleName, lastName);
		}
		return name;
	}

	/**
	 * Sets the user as disabled
	 * 
	 * @param username
	 * @return
	 */
	public boolean deleteUser(String username)
	{
		User user = findUserByUsername(username);
		try
		{
			user.setEnabled(false);
			userRepository.save(user);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public boolean userExists(String username)
	{
		return userRepository.existsByUsername(username);
	}

	
	public boolean loginDemo()
	{
		return findUserByUsername("John") != null;
	}

	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}

}
