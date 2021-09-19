package hadi.springSecurity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hadi.springSecurity.BL.UserBL;
import hadi.springSecurity.beans.entities.User;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin
public class UserController
{
	private final UserBL userBL;
	
	@Autowired
	public UserController(UserBL userBL)
	{
		this.userBL = userBL;
	}
	
	@GetMapping("/hello")
	public String hello()
	{
		return "Hello!";
	}
	
	@GetMapping("/loginDemo")
	public boolean loginDemo()
	{
		return userBL.loginDemo();
	}
	
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers()
	{
		return userBL.getAllUsers();
	}
}
