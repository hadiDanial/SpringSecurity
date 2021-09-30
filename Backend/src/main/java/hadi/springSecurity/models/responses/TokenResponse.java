package hadi.springSecurity.models.responses;

public class TokenResponse
{
	private String authenticationToken;
	private String message;
	
	public TokenResponse()
	{
	}

	public TokenResponse(String authenticationToken, String message)
	{
		super();
		this.authenticationToken = authenticationToken;
		this.message = message;
	}

	public String getAuthenticationToken()
	{
		return authenticationToken;
	}

	public void setAuthenticationToken(String authenticationToken)
	{
		this.authenticationToken = authenticationToken;
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
