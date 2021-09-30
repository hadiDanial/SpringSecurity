package hadi.springSecurity.models.responses;

import hadi.springSecurity.models.entities.Token;

public class LoginResponse
{
	private Token token;
	private String message;

	public LoginResponse()
	{
		super();
	}
	
	public LoginResponse(Token token, String message)
	{
		super();
		this.token = token;
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
	
	
}
