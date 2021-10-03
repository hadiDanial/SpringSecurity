package hadi.springSecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hadi.springSecurity.exceptions.TokenException;
import hadi.springSecurity.models.entities.Token;
import hadi.springSecurity.models.requests.LoginRequest;
import hadi.springSecurity.models.requests.ValidateTokenRequest;
import hadi.springSecurity.models.responses.LoginResponse;
import hadi.springSecurity.models.responses.TokenResponse;
import hadi.springSecurity.services.AuthenticationService;

@RestController
@RequestMapping(path = "/auth")
public class AuthorizationController
{
	private final AuthenticationService authService;

	@Autowired
	public AuthorizationController(AuthenticationService authService)
	{
		super();
		this.authService = authService;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestHeader("username") String username,
			@RequestHeader("password") String password) // Header
//	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) // Body
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
	
	@PostMapping("/logout")
	public ResponseEntity<Void> logout(String refreshToken) // Params
	{
		try
		{
			authService.logout(refreshToken);
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
