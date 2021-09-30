package hadi.springSecurity.models.requests;

public class ValidateTokenRequest
{
	private String authenticationToken;
	private String refreshToken;
	
	public ValidateTokenRequest()
	{
		super();
	}
	
	public ValidateTokenRequest(String authenticationToken, String refreshToken)
	{
		super();
		this.authenticationToken = authenticationToken;
		this.refreshToken = refreshToken;
	}

	public String getAuthenticationToken()
	{
		return authenticationToken;
	}

	public void setAuthenticationToken(String authenticationToken)
	{
		this.authenticationToken = authenticationToken;
	}

	public String getRefreshToken()
	{
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken)
	{
		this.refreshToken = refreshToken;
	}
}
