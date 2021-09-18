package hadi.springSecurity.BL;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hadi.springSecurity.beans.embeddables.Credential;
import hadi.springSecurity.beans.embeddables.Name;
import hadi.springSecurity.beans.entities.User;
import hadi.springSecurity.configuration.Properties;
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
	
	
	public boolean createUser(String username, String email, String password, String firstName, String middleName, String lastName)
	{
		Name name = generateName(firstName, middleName, lastName);
		Credential credentials = generateCredentials(password);
		User user = new User(username, email, name, credentials);
		try
		{
			userRepository.save(user);
			return true;
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}


	public boolean updatePassword(String username, String password)
	{
		User user = findUserByUsername(username);
		if(user == null)
			return false;
		try
		{
			Credential credentials = generateCredentials(password);
			user.setCredentials(credentials);
			userRepository.save(user);
			return true;	
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public boolean updateName(String username, String firstName, String middleName, String lastName)
	{
		User user = findUserByUsername(username);
		if(user == null)
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
		return new Credential(hashedPassword, LocalDateTime.now().plusDays(properties.getCredentialExpirationDays()));
	}
	


	private Name generateName(String firstName, String middleName, String lastName)
	{
		Name name;
		if(middleName == null || middleName.equals(""))
		{
			name = new Name(firstName, lastName);			
		}
		else
		{			
			name = new Name(firstName, middleName, lastName);
		}
		return name;
	}


	public boolean loginDemo()
	{
		return findUserByUsername("John") != null;
	}
	
}
