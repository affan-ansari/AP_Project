package application;

public final class LoginSession 
{
	private static LoginSession instance;
	
	private String username;
	private String role;
	
	private LoginSession(String username, String role)
	{
		this.username = username;
		this.role = role;
	}
	
	public static LoginSession getInstance(String username, String role)
	{
		if (instance == null)
		{
			instance = new LoginSession(username,role);
		}
		return instance;
	}
	public String getUsername()
	{
		return this.username;
	}
	public String getRole()
	{
		return this.role;
	}
	public void endSession()
	{
		username = "";
		role = "";
	}
}
