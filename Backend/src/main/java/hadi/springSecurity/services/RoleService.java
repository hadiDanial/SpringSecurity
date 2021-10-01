package hadi.springSecurity.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hadi.springSecurity.exceptions.RoleException;
import hadi.springSecurity.models.entities.Authority;
import hadi.springSecurity.models.entities.Role;
import hadi.springSecurity.models.responses.MessageBoolResponse;
import hadi.springSecurity.repositories.AuthorityRepository;
import hadi.springSecurity.repositories.RoleRepository;

@Service
public class RoleService
{
	private final RoleRepository roleRepository;
	private final AuthorityRepository authorityRepository;
	
	@Autowired
	public RoleService(RoleRepository roleRepository, AuthorityRepository authorityRepository)
	{
		super();
		this.roleRepository = roleRepository;
		this.authorityRepository = authorityRepository;
	}
	
	public MessageBoolResponse addNewRole(String roleName)
	{
		if(roleRepository.existsByRoleIdentifier(roleName.toUpperCase()))
		{
			return new MessageBoolResponse("Role "+ roleName +" already exists.", false);
		}
		else
		{
			Role role = new Role(roleName);
			roleRepository.save(role);
			return new MessageBoolResponse("Role "+ roleName +" added successfully.", true);
		}
	}

	public MessageBoolResponse addNewAuthority(String authorityName)
	{
		if(authorityRepository.existsByAuthorityIdentifier(authorityName.toUpperCase()))
		{
			return new MessageBoolResponse("Authority "+ authorityName +" already exists.", false);
		}
		else
		{
			Authority authority = new Authority(authorityName);
			authorityRepository.save(authority);
			return new MessageBoolResponse("Authority "+ authorityName +" added successfully.", true);
		}
	}

	public MessageBoolResponse addAuthorityToRole(String authorityName, String roleName)
	{
		MessageBoolResponse response;
		try
		{
			Authority authority = findAuthorityByName(authorityName);
			Role role = findRoleByName(roleName);
			List<Authority> authorities = role.getAuthorities();
			if(authorities.contains(authority))
			{
				throw new RoleException("Role already has authority.");
			}
			authorities.add(authority);
			role.setAuthorities(authorities);
			roleRepository.save(role);			
			response = new MessageBoolResponse("Added " + authority.getAuthorityName() + " to " + role.getRoleName() + ".", true);
		}
		catch(RoleException e)
		{
			response = new MessageBoolResponse(e.getMessage(), false);			
		}
		return response;
	}

	public MessageBoolResponse removeAuthorityFromRole(String authorityName, String roleName)
	{
		MessageBoolResponse response;
		try
		{
			Authority authority = findAuthorityByName(authorityName);
			Role role = findRoleByName(roleName);
			List<Authority> authorities = role.getAuthorities();
			if(!authorities.contains(authority))
			{
				throw new RoleException("Role doesn't have this authority.");
			}
			authorities.remove(authority);
			role.setAuthorities(authorities);
			roleRepository.save(role);			
			response = new MessageBoolResponse("Removed " + authority.getAuthorityName() + " from " + role.getRoleName() + ".", true);
		}
		catch(RoleException e)
		{
			response = new MessageBoolResponse(e.getMessage(), false);			
		}
		return response;
	}

	private Role findRoleByName(String roleName) throws RoleException
	{
		return roleRepository.findByRoleIdentifier(roleName.toUpperCase())
				.orElseThrow(()-> new RoleException("Role not found."));
	}

	private Authority findAuthorityByName(String authorityName) throws RoleException
	{
		return authorityRepository.findByAuthorityIdentifier(authorityName.toUpperCase())
				.orElseThrow(()-> new RoleException("Authority not found."));
	}


	
}
