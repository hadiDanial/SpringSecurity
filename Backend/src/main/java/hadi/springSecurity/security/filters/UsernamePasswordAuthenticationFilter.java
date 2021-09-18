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
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import hadi.springSecurity.security.authentications.UsernamePasswordAuthentication;

@Component
public class UsernamePasswordAuthenticationFilter extends OncePerRequestFilter
{
    private final AuthenticationManager authenticationManager;
	
    @Autowired
    @Lazy
	public UsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager)
	{
		this.authenticationManager = authenticationManager;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException
	{
		var username = request.getHeader("username");
		var password = request.getHeader("password");
		Authentication a = new UsernamePasswordAuthentication(username, password);
        a = authenticationManager.authenticate(a);
	}
	
	@Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }
}
