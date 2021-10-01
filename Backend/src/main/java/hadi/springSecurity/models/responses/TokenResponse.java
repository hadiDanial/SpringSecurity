package hadi.springSecurity.models.responses;

public class TokenResponse
{
	private String accessToken;
	private String refreshToken;
	private String message;
	private boolean success;
	
	public TokenResponse()
	{
	}

	public TokenResponse(String accessToken, String refreshToken, String message, boolean success)
	{
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.message = message;
		this.success = success;
	}

	public String getAccessToken()
	{
		return accessToken;
	}

	public void setAccessToken(String accessToken)
	{
		this.accessToken = accessToken;
	}

	public String getRefreshToken()
	{
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken)
	{
		this.refreshToken = refreshToken;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
	}
	
	
	
}
