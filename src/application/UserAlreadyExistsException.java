package application;

@SuppressWarnings("serial")
public class UserAlreadyExistsException extends Exception {
	public UserAlreadyExistsException(String message)
	{
		super(message);
	}
}
