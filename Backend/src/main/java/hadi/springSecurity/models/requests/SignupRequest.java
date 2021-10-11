package hadi.springSecurity.models.requests;

import hadi.springSecurity.models.embeddables.Name;

public class SignupRequest
{
	private String username;
	private String email;
	private String password;
	private Name name;
	public SignupRequest()
	{
		super();
	}
	
	public SignupRequest(String username, String email, String password, Name name)
	{
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.name = name;
	}

	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}

	public Name getName()
	{
		return name;
	}

	public void setName(Name name)
	{
		this.name = name;
	}
	
}
