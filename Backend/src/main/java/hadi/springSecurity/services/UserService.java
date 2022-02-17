package hadi.springSecurity.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import hadi.springSecurity.configuration.Properties;
import hadi.springSecurity.exceptions.RoleException;
import hadi.springSecurity.models.embeddables.Credential;
import hadi.springSecurity.models.embeddables.Name;
import hadi.springSecurity.models.entities.Role;
import hadi.springSecurity.models.entities.Token;
import hadi.springSecurity.models.entities.UnverifiedUser;
import hadi.springSecurity.models.entities.User;
import hadi.springSecurity.models.responses.MessageBoolResponse;
import hadi.springSecurity.models.security.SecurityUser;
import hadi.springSecurity.repositories.UnverifiedUserRepository;
import hadi.springSecurity.repositories.UserRepository;

@Service
public class UserService implements UserDetailsManager
{
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UnverifiedUserRepository unverifiedRepository;
	private final Properties properties;
	private final RoleService roleService;
	private final EmailService emailService;

	@Autowired
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UnverifiedUserRepository unverifiedRepository,
			Properties properties, RoleService roleService, EmailService emailService)
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
	{
		super();
		this.properties = properties;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleService = roleService;
		this.unverifiedRepository = unverifiedRepository;
		this.emailService = emailService;
	}

	public ResponseEntity<MessageBoolResponse> createNewUser(String username, String email, String password, Name name)
	{
		Credential credentials = generateCredentials(password);
		User user = new User(username, email, name, credentials);
		MessageBoolResponse response = new MessageBoolResponse();
		try
		{
			userRepository.save(user);
			UnverifiedUser unverified = new UnverifiedUser(username);
			unverifiedRepository.save(unverified);
			emailService.sendSimpleMail(user.getEmail(), "Verify your account - Hadi's Security App",
					"Please go to " + properties.getAppURL() + "user/verify/"+unverified.getVerificationUUID() + " to verify your account.\nThank you for joining us!");
			response.setMessage("User " + user.getUsername() + " created successfully. Please check your inbox and verify your e-mail address.");
			response.setResult(true);
			return new ResponseEntity<MessageBoolResponse>(response, HttpStatus.OK);
		} catch (Exception e)
		{
			response.setMessage("Failed to register " + user.getUsername() + ": " + e.getMessage());
			response.setResult(false);
			return new ResponseEntity<MessageBoolResponse>(response, HttpStatus.NOT_ACCEPTABLE);
		}
	}
	public ResponseEntity<MessageBoolResponse> verifyUser(String verificationUUID)
	{
		UnverifiedUser unverified = unverifiedRepository.findUnverifiedUserByVerificationUUID(verificationUUID).get();
		// TODO: Add expiration to the UUID, if the time limit has passed, the user will need to request another verification code?
		MessageBoolResponse response;
		if(unverified != null)
		{
			String username = unverified.getUsername();
			User user = findUserByUsername(username);
			user.setVerified(true);
			userRepository.save(user);
			unverifiedRepository.delete(unverified);
			response = new MessageBoolResponse("User " + username + " was verified successfully. Welcome aboard!", true);
			emailService.sendSimpleMail(user.getEmail(), "Account Verified!", "Thank you for verifying your account! Enjoy your stay ;)");
			return new ResponseEntity<MessageBoolResponse>(response, HttpStatus.OK);
		}
		else
		{
			response = new MessageBoolResponse("Verification error.", false);
			return new ResponseEntity<MessageBoolResponse>(response, HttpStatus.BAD_REQUEST);
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

	/**
	 * Returns user by username. Throws <code>UsernameNotFoundException</code> if the username isn't found.
	 * @param username
	 * @return
	 */
	public User findUserByUsername(String username) throws UsernameNotFoundException
	{
		Optional<User> optionalUser = userRepository.findUserByUsernameIgnoreCase(username);
		User user = optionalUser.orElseThrow(()-> new UsernameNotFoundException(username + " not found."));
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


	public boolean userExists(String username)
	{
		return userRepository.existsByUsername(username);
	}

	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		return new SecurityUser(findUserByUsername(username));
	}

	@Override
	public void createUser(UserDetails user)
	{
		createNewUser(user.getUsername(), null, user.getPassword(), null);
	}

	@Override
	public void updateUser(UserDetails user)
	{
	}

	@Override
	public void deleteUser(String username)
	{
		try
		{
			User user = findUserByUsername(username);
			user.setEnabled(false);
			userRepository.save(user);
		}
		catch (Exception e)
		{
			throw new UsernameNotFoundException("Failed to delete " + username);
		}
	}

	@Override
	public void changePassword(String oldPassword, String newPassword)
	{
	}

	public MessageBoolResponse addRoleToUser(User user, String roleName)
	{
		Role role;
		try
		{
			role = roleService.findRoleByName(roleName);			
		} catch (RoleException e)
		{
			return new MessageBoolResponse("Role " + roleName + " doesn't exist.", false);
		}
		User u = userRepository.findById(user.getId()).get();
		List<Role> userRoles = u.getRoles();
		if(userRoles.contains(role))
		{
			return new MessageBoolResponse("User " + user.getUsername() +" already has role " + roleName + ".", false);
		}
		userRoles.add(role);
		u.setRoles(userRoles);
		userRepository.save(u);
		return new MessageBoolResponse("Role " + roleName + " added to " + user.getUsername() + " successfully.", true);
	}
	
	public MessageBoolResponse removeRoleFromUser(User user, String roleName)
	{
		Role role;
		try
		{
			role = roleService.findRoleByName(roleName);			
		} catch (RoleException e)
		{
			return new MessageBoolResponse("Role " + roleName + " doesn't exist.", false);
		}
		User u = userRepository.findById(user.getId()).get();
		List<Role> userRoles = u.getRoles();
		if(userRoles.contains(role))
		{
			userRoles.remove(role);
			u.setRoles(userRoles);
			userRepository.save(u);
			return new MessageBoolResponse("Role " + roleName + " removed from " + user.getUsername() + " successfully.", true);
		}
		return new MessageBoolResponse("User " + user.getUsername() +" doesn't have role " + roleName + ".", false);
	}

	public void updateLastAccessDate(User user)
	{
		user.setLastAccessDate(Instant.now());
		userRepository.save(user);
	}

	public void updateLastLoginDate(User user)
	{
		user.setLastLoginDate(Instant.now());
		userRepository.save(user);
	}
}
