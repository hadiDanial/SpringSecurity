package hadi.springSecurity.exceptions;

public class RoleException extends RuntimeException
{
	private static final long serialVersionUID = -1858269301036499002L;

	public RoleException()
	{
		super();
	}

	public RoleException(String message)
	{
		super(message);
	}
}
