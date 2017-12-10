
public class User 
{
	private String userName;
	private String passcode;
	
	public User(String aUserName, String aPasscode)
	{
		userName = aUserName;
		passcode = aPasscode;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public String getPasscode()
	{
		return passcode;
	}
	
	/**
    Change mailbox's passcode.
    @param newPasscode the new passcode
	 */
	public void setPasscode(String newPasscode)
	{
		passcode = newPasscode;
	}
}
