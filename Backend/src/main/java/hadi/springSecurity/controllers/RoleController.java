package hadi.springSecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hadi.springSecurity.models.responses.MessageBoolResponse;
import hadi.springSecurity.services.RoleService;

@RestController
@RequestMapping(path = "/roles")
@CrossOrigin
public class RoleController
{
	private final RoleService roleService;
	
	@Autowired
	public RoleController(RoleService roleService)
	{
		super();
		this.roleService = roleService;
	}

	@PostMapping(path = "/addNewRole")
	public ResponseEntity<MessageBoolResponse> addNewRole(String roleName)
	{
		MessageBoolResponse response = roleService.addNewRole(roleName);
		return generateMessageResponseEntity(response);		
	}
	
	@PostMapping(path = "/addNewAuthority")
	public ResponseEntity<MessageBoolResponse> addNewAuthority(String authorityName)
	{
		MessageBoolResponse response = roleService.addNewAuthority(authorityName);
		return generateMessageResponseEntity(response);		
	}
	
	@PostMapping(path = "/addAuthorityToRole")
	public ResponseEntity<MessageBoolResponse> addAuthorityToRole(String authorityName, String roleName)
	{
		MessageBoolResponse response = roleService.addAuthorityToRole(authorityName, roleName);
		return generateMessageResponseEntity(response);		
	}
	
	@PostMapping(path = "/removeAuthorityFromRole")
	public ResponseEntity<MessageBoolResponse> removeAuthorityFromRole(String authorityName, String roleName)
	{
		MessageBoolResponse response = roleService.removeAuthorityFromRole(authorityName, roleName);
		return generateMessageResponseEntity(response);		
	}
	
	
	private ResponseEntity<MessageBoolResponse> generateMessageResponseEntity(MessageBoolResponse response)
	{
		ResponseEntity<MessageBoolResponse> entity;
		if(response.isResult())
		{
			entity = new ResponseEntity<MessageBoolResponse>(response, HttpStatus.OK);
		}
		else
		{
			entity = new ResponseEntity<MessageBoolResponse>(response, HttpStatus.CONFLICT);			
		}
		return entity;
	}
}
