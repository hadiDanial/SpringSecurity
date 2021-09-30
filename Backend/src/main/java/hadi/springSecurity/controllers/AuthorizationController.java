package hadi.springSecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hadi.springSecurity.models.requests.LoginRequest;
import hadi.springSecurity.models.responses.AuthenticationResponse;
import hadi.springSecurity.services.AuthorizationService;

@RestController
@RequestMapping(path = "/auth")
public class AuthorizationController
{
	private final AuthorizationService authService;

	@Autowired
	public AuthorizationController(AuthorizationService authService)
	{
		super();
		this.authService = authService;
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest)
	{
		ResponseEntity<AuthenticationResponse> response;
		AuthenticationResponse authResponse;
		try
		{
			authResponse = authService.login(loginRequest);
		} 
		catch (BadCredentialsException e)
		{
			response = new ResponseEntity<>(HttpStatus.FORBIDDEN);
			return response;
		}

		response = new ResponseEntity<>(authResponse, HttpStatus.OK);

		return response;
	}
}
