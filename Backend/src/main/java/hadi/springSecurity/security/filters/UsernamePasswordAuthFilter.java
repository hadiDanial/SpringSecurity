package hadi.springSecurity.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.filter.OncePerRequestFilter;

import hadi.springSecurity.security.authentications.UsernamePasswordAuthentication;

public class UsernamePasswordAuthFilter extends OncePerRequestFilter
{
	private final AuthenticationManager authenticationManager;

	@Autowired
	@Lazy
	public UsernamePasswordAuthFilter(AuthenticationManager authenticationManager)
	{
		this.authenticationManager = authenticationManager;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException
	{
		var username = request.getHeader("username");
		var password = request.getHeader("password");
		if(username != null && password != null)
		{
			try
			{
				UsernamePasswordAuthentication a = new UsernamePasswordAuthentication(username, password, null);
				a = (UsernamePasswordAuthentication) authenticationManager.authenticate(a);							
			} catch (AuthenticationException e)
			{
				response.setStatus(401);
			}
		}
		filterChain.doFilter(request, response);
//		response.setHeader(ALREADY_FILTERED_SUFFIX, ALREADY_FILTERED_SUFFIX);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException
	{
		return !request.getServletPath().equals("/auth/login");
	}

}
