package hadi.springSecurity.models.responses;

import hadi.springSecurity.models.entities.Token;
import hadi.springSecurity.models.entities.User;

public class LoginResponse
{
	private Token token;
	private User user;
	private String message;

	public LoginResponse()
	{
		super();
	}

	public LoginResponse(Token token, User user, String message)
	{
		super();
		this.token = token;
		this.user = user;
		this.message = message;
	}

	public Token getToken()
	{
		return token;
	}

	public void setToken(Token token)
	{
		this.token = token;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}	
}
