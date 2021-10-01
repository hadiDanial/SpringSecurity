package hadi.springSecurity.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hadi.springSecurity.exceptions.TokenException;
import hadi.springSecurity.models.entities.Token;
import hadi.springSecurity.models.entities.User;
import hadi.springSecurity.models.requests.LoginRequest;
import hadi.springSecurity.models.requests.ValidateTokenRequest;
import hadi.springSecurity.models.responses.LoginResponse;
import hadi.springSecurity.models.responses.TokenResponse;
import io.jsonwebtoken.JwtException;

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

	public LoginResponse login(LoginRequest loginRequest)
	{
//		authManager.authenticate(null)
		User user = userService.findUserByUsername(loginRequest.getUsername());
		if(user == null)
		{
			throw new BadCredentialsException("Login failed, " + loginRequest.getUsername() + " doesn't exist.");
		}
		if(passwordEncoder.matches(loginRequest.getPassword(), user.getCredentials().getPassword()))
		{
			Token token = tokenService.generateToken(user);
			user.setLastAccessDate(Instant.now());
			LoginResponse response = new LoginResponse(token,user, "Logged in successfully");
			return response;
		}
		throw new BadCredentialsException("Login failed, bad credentials for " + loginRequest.getUsername() + ".");
	}
	
	public void logout(String refreshToken)
	{
		tokenService.deleteToken(refreshToken);
	}

	
	public TokenResponse validate(ValidateTokenRequest validateTokenRequest)
	{
		boolean isValidAccessToken, isValidRefreshToken;
		try
		{
			isValidRefreshToken = tokenService.isValidAuthToken(validateTokenRequest.getRefreshToken());
		} 
		catch (JwtException e)
		{
			throw new TokenException("Please log in again.");
		}
		try
		{
			isValidAccessToken = tokenService.isValidAuthToken(validateTokenRequest.getAccessToken());
			if(isValidRefreshToken && isValidAccessToken)
			{
				return new TokenResponse(validateTokenRequest.getAccessToken(), validateTokenRequest.getRefreshToken(), "Success, access token", true);
			}
		} 
		catch (JwtException e)
		{
			if(isValidRefreshToken)
			{
				Token token = tokenService.updateAccessToken(validateTokenRequest.getRefreshToken());
				return new TokenResponse(token.getAccessToken(), token.getRefreshToken(), "Success, refresh token", true); 				
			}
		}
		throw new TokenException("Please log in again.");
	}
}
