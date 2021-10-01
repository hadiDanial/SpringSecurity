package hadi.springSecurity.models.responses;

public class MessageBoolResponse
{
	private String message;
	private boolean result;
	
	public MessageBoolResponse()
	{
	}
	
	public MessageBoolResponse(String message, boolean result)
	{
		super();
		this.message = message;
		this.result = result;
	}

	public String getMessage()
	{
		return message;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	public boolean isResult()
	{
		return result;
	}
	
	public void setResult(boolean result)
	{
		this.result = result;
	}
}
