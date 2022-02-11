package hadi.springSecurity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hadi.springSecurity.models.entities.User;
import hadi.springSecurity.models.responses.MessageBoolResponse;
import hadi.springSecurity.services.RoleService;
import hadi.springSecurity.services.UserService;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController
{
	private final UserService userService;
	@Autowired
	public UserController(UserService userService)
	{
		this.userService = userService;
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>>getAllUsers()
	{
		try
		{
			List<User> users = userService.getAllUsers();
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		} 
		catch (Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/addRoleToUser")
	public ResponseEntity<MessageBoolResponse> addRoleToUser(@RequestBody User user, String role)
	{
		MessageBoolResponse response = userService.addRoleToUser(user, role);
		return returnResponseWithCode(response);
	}
	
	@DeleteMapping("/removeRoleFromUser")
	public ResponseEntity<MessageBoolResponse> removeRoleFromUser(@RequestBody User user, String role)
	{
		MessageBoolResponse response = userService.removeRoleFromUser(user, role);
		return returnResponseWithCode(response);
	}

	private ResponseEntity<MessageBoolResponse> returnResponseWithCode(MessageBoolResponse response)
	{
		if(response.isResult())
		{
			return new ResponseEntity<MessageBoolResponse>(response, HttpStatus.OK);
		}
		else 
		{
			return new ResponseEntity<MessageBoolResponse>(response, HttpStatus.CONFLICT);			
		}
	}
	
	@GetMapping("/hello")
	public String hello()
	{
		return "Hello, " + SecurityContextHolder.getContext().getAuthentication().getName() + "!";
	}

}
