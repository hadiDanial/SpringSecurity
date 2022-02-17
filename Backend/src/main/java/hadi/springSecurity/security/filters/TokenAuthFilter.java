package hadi.springSecurity.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.filter.OncePerRequestFilter;

import hadi.springSecurity.security.authentications.TokenAuthentication;
import hadi.springSecurity.security.authentications.UsernamePasswordAuthentication;

public class TokenAuthFilter extends OncePerRequestFilter
{
	private final AuthenticationManager authenticationManager;

	@Autowired
	@Lazy
	public TokenAuthFilter(AuthenticationManager authenticationManager)
	{
		this.authenticationManager = authenticationManager;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException
	{	
			var accessToken = request.getHeader("Authorization");
//			var refreshToken = request.getHeader("refreshToken"); 
			if(accessToken != null) // && refreshToken != null)
			{
				try
				{
					Authentication a = new TokenAuthentication(accessToken, null, null);
					a = authenticationManager.authenticate(a);	
					SecurityContext securityContext = SecurityContextHolder.getContext();
					securityContext.setAuthentication(a);
					HttpSession session = request.getSession();
					session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
					filterChain.doFilter(request, response);
				} catch (Exception e)
				{
					response.setStatus(401);
				}
			}
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException
	{
		return request.getServletPath().equals("/auth/login") 
			|| request.getServletPath().equals("/auth/validate")
	}
}
