package hadi.springSecurity.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hadi.springSecurity.models.responses.MessageBoolResponse;
import hadi.springSecurity.services.UserService;

@RestController
@RequestMapping(path = "/profile")
public class UserProfileController
{
	private final UserService userService;
	
	@Autowired
	public UserProfileController(UserService userService)
	{
		super();
		this.userService = userService;
	}
	
	@PostMapping(path="updateAboutMe")
	public ResponseEntity<MessageBoolResponse> updateAboutMe(@RequestBody Map<String, String> about)
	{
		MessageBoolResponse response = userService.updateAboutMe(about.get("about"));
		if(response != null)
			return new ResponseEntity<MessageBoolResponse>(response, HttpStatus.OK);
		return new ResponseEntity<MessageBoolResponse>(
				new MessageBoolResponse("Failed to update", false), HttpStatus.I_AM_A_TEAPOT);		
	}
}
