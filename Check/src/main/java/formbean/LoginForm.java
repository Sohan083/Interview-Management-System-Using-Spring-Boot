package formbean;

public class LoginForm {

	private String name;
	private String pass;
	
	public LoginForm() {
		
	}
	
	public LoginForm(String name, String pass)
	{
		this.name = name;
		this.pass = pass;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return name;
	}
	
	public void setPass(String pass)
	{
		this.pass = pass;
	}
	public String getPass()
	{
		return pass;
	}
}
