package hadi.springSecurity.services;

import java.time.Instant;

import javax.el.MethodNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hadi.springSecurity.configuration.Properties;
import hadi.springSecurity.models.entities.Token;
import hadi.springSecurity.models.entities.User;
import hadi.springSecurity.models.requests.LoginRequest;
import hadi.springSecurity.models.requests.ValidateTokenRequest;
import hadi.springSecurity.models.responses.TokenResponse;
import hadi.springSecurity.security.services.JwtProvider;

@Service
public class AuthorizationService
{
	private final UserService userService;
	private final TokenService tokenService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authManager;

	@Autowired
	public AuthorizationService(AuthenticationManager authManager, 
								UserService userService, TokenService tokenService, PasswordEncoder passwordEncoder)
	{
		super();
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.authManager = authManager;
		this.tokenService = tokenService;
	}

	public Token login(LoginRequest loginRequest)
	{
//		authManager.authenticate(null)
		User user = userService.findUserByUsername(loginRequest.getUsername());
		if(user == null)
		{
			throw new BadCredentialsException("Login failed, " + loginRequest.getUsername() + " doesn't exist.");
		}
		if(passwordEncoder.matches(loginRequest.getPassword(), user.getCredentials().getPassword()))
		{
			Token response = tokenService.generateToken(user);
			user.setLastAccessDate(Instant.now());
			return response;
		}
		throw new BadCredentialsException("Login failed, bad credentials for " + loginRequest.getUsername() + ".");
	}
	
	public void logout(String refreshToken)
	{
		tokenService.deleteToken(refreshToken);
	}

	public String refreshAuthToken(String refreshToken)
	{
		// TODO Auto-generated method stub
		throw new MethodNotFoundException();
	}

	public TokenResponse validate(ValidateTokenRequest validateTokenRequest)
	{
		//"Session expired, please log in again..." "Invalid credentials, log in again..."
		return null;
	}
}
