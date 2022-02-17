package hadi.springSecurity.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hadi.springSecurity.exceptions.TokenException;
import hadi.springSecurity.models.embeddables.Name;
import hadi.springSecurity.models.entities.Token;
import hadi.springSecurity.models.entities.User;
import hadi.springSecurity.models.requests.LoginRequest;
import hadi.springSecurity.models.requests.ValidateTokenRequest;
import hadi.springSecurity.models.responses.LoginResponse;
import hadi.springSecurity.models.responses.MessageBoolResponse;
import hadi.springSecurity.models.responses.TokenResponse;
import hadi.springSecurity.services.AuthenticationService;
import hadi.springSecurity.services.UserService;

@RestController
@RequestMapping(path = "/auth")
@CrossOrigin
public class AuthenticationController
{
	private final AuthenticationService authService;
	private final UserService userService;
	@Autowired
	public AuthenticationController(AuthenticationService authService, UserService userService)
	{
		super();
		this.authService = authService;
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestHeader("username") String username,
			@RequestHeader("password") String password)
	{
		if(username == null || password == null)
		{			
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

		LoginRequest loginRequest = new LoginRequest(username, password);

		return authService.login(loginRequest);
	}
	
	@GetMapping("/getUserByToken")
	public ResponseEntity<User> getUserByToken(@RequestHeader("Authorization") String Authorization)
	{
		if(Authorization == null)
		{			
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		User user = authService.getUserFromAccessToken(Authorization);
		if(user != null)
			return new ResponseEntity<User>(user, HttpStatus.OK);
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<Void> logout(@RequestHeader("Authorization") String Authorization) 
	{
		try
		{
			authService.logout(Authorization);
			return ResponseEntity.ok(null);
		} catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PostMapping("/validate")
	public ResponseEntity<TokenResponse> validate(@RequestBody ValidateTokenRequest validateTokenRequest)
	{
		TokenResponse response;
		try
		{
			response = authService.validate(validateTokenRequest);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (TokenException e)
		{
			response = new TokenResponse();
			response.setMessage(e.getMessage());
			response.setSuccess(false);
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
	}
}
