package hadi.springSecurity.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import hadi.springSecurity.models.entities.User;
import hadi.springSecurity.models.security.SecurityUser;

public class AuthenticationUserMatcher
{

	public AuthenticationUserMatcher()
	{
	}
	public static boolean idMatchesAuthenticatedUser(Long userId)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
		return securityUser.getUser().getId() == userId;
	}
	public static User getAuthenticatedUser()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
		return securityUser.getUser();
	}
}
