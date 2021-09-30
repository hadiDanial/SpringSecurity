package hadi.springSecurity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hadi.springSecurity.models.entities.User;
import hadi.springSecurity.services.UserService;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin
public class UserController
{
	private final UserService userBL;
	
	@Autowired
	public UserController(UserService userBL)
	{
		this.userBL = userBL;
	}
	
	@GetMapping("/hello")
	public String hello()
	{
		return "Hello!";
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>>getAllUsers()
	{
		try
		{
			List<User> users = userBL.getAllUsers();
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		} 
		catch (Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
