package tutorialNinjaProject.QA.pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
	WebDriver d;
	public LoginPage(WebDriver d)
	{
		this.d =d;
		PageFactory.initElements(d, this);
		
	}
	@FindBy(xpath="//input[@id='input-email']")
	private WebElement userEmail;
	
	@FindBy(xpath = "//input[@id='input-password']")
	private WebElement userPassword;
	
	@FindBy(xpath = "//input[@value='Login']")
	private WebElement LoginButton;
	
	
	public WebElement userEmail()
	{
		return userEmail;
	}
	
	public WebElement userPassword()
	{
		return userPassword;
	}
	
	public WebElement LoginButton()
	{
		return LoginButton;
	}

}
