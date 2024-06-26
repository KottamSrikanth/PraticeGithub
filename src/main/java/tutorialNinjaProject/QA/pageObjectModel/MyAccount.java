package tutorialNinjaProject.QA.pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccount {
	
	WebDriver d;
	public MyAccount(WebDriver d)
	{
		this.d=d;
		PageFactory.initElements(d, this);
	}
	@FindBy(xpath="//span[contains(@class,'hidden-xs') and text()='My Account']")
	private WebElement myAccount;
	
	@FindBy(linkText = "Login")
	private WebElement LoginButton;
	
	@FindBy(linkText = "Register")
	private WebElement Register;
	
	public WebElement myAccount()
	{
		return myAccount;
	}
	
	public WebElement LoginButton()
	{
		return LoginButton;
	}
	
	public WebElement Register()
	{
		return Register;
	}

}
