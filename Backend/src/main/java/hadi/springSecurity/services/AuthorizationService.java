package hadi.springSecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hadi.springSecurity.models.entities.User;
import hadi.springSecurity.models.requests.LoginRequest;
import hadi.springSecurity.models.responses.AuthenticationResponse;
import hadi.springSecurity.security.services.JwtProvider;

@Service
public class AuthorizationService
{
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authManager;
	private final JwtProvider jwtProvider;
	@Autowired
	public AuthorizationService(JwtProvider jwtProvider, AuthenticationManager authManager, 
								UserService userBL, PasswordEncoder passwordEncoder)
	{
		super();
		this.userService = userBL;
		this.passwordEncoder = passwordEncoder;
		this.authManager = authManager;
		this.jwtProvider = jwtProvider;
	}

	public AuthenticationResponse login(LoginRequest loginRequest)
	{
//		authManager.authenticate(null)
		User user = userService.findUserByUsername(loginRequest.getUsername());
		if(user == null || 
				passwordEncoder.matches(loginRequest.getPassword(), user.getCredentials().getPassword()))
		{
			AuthenticationResponse response = jwtProvider.generateAuthResponse(user);
			return response;
		}
		throw new BadCredentialsException("Login failed, bad credentials for " + loginRequest.getUsername());
	}
}
