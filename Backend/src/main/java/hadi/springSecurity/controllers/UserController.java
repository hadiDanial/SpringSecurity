package hadi.springSecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hadi.springSecurity.BL.UserBL;

@RestController
@RequestMapping(path = "/user")
public class UserController
{
	private final UserBL userBL;
	
	@Autowired
	public UserController(UserBL userBL)
	{
		this.userBL = userBL;
	}

	@GetMapping("/loginDemo")
	public boolean loginDemo()
	{
		return this.userBL.loginDemo();
	}
}
