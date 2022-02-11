package hadi.springSecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hadi.springSecurity.exceptions.TokenException;
import hadi.springSecurity.models.entities.Token;
import hadi.springSecurity.models.entities.User;
import hadi.springSecurity.models.requests.LoginRequest;
import hadi.springSecurity.models.requests.ValidateTokenRequest;
import hadi.springSecurity.models.responses.LoginResponse;
import hadi.springSecurity.models.responses.TokenResponse;
import hadi.springSecurity.services.AuthenticationService;

@RestController
@RequestMapping(path = "/auth")
@CrossOrigin
public class AuthenticationController
{
	private final AuthenticationService authService;

	@Autowired
	public AuthenticationController(AuthenticationService authService)
	{
		super();
		this.authService = authService;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestHeader("username") String username,
			@RequestHeader("password") String password)
	{
		if(username == null || password == null)
		{			
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		LoginResponse loginResponse;
		LoginRequest loginRequest = new LoginRequest(username, password);
		ResponseEntity<LoginResponse> response;
		try
		{
			loginResponse = authService.login(loginRequest);
		} 
		catch (BadCredentialsException e)
		{
			loginResponse = new LoginResponse(null, null, e.getMessage());
			response = new ResponseEntity<>(loginResponse, HttpStatus.FORBIDDEN);
			return response;
		}
		response = new ResponseEntity<>(loginResponse, HttpStatus.OK);

		return response;
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
