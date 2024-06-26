package tutorialNinjaProject.QA.pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Regristerpage {
	WebDriver d;
	public Regristerpage(WebDriver d)
	{
		this.d= d;
		PageFactory.initElements(d,this);
		
	}
	@FindBy(xpath = "//input[@id='input-firstname']")
	private WebElement firstName;
	
	@FindBy(xpath = "//input[@id='input-lastname']")
	private WebElement lastName;
	
	@FindBy(xpath = "//input[@id='input-email']")
	private WebElement email;
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	private WebElement telephone;
	
	@FindBy(xpath = "//input[@id='input-password']")
	private WebElement password;
	
	@FindBy(xpath = "//input[@id='input-confirm']")
	private WebElement passwordConfirm;
	
	@FindBy(xpath = "//input[@name='agree']")
	private WebElement privacyPolicy;
	
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continuebutton;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement WarningMsg;
	
	public WebElement firstName()
	{
		return firstName;
	}
	
	public WebElement lastName()
	{
		return lastName;
	}
	

	
	public WebElement email()
	{
		return email;
	}
	
	public WebElement telephone()
	{
		return telephone;
	}
	
	public WebElement password()
	{
		return password;
	}
	
	public WebElement passwordConfirm()
	{
		return passwordConfirm;
	}
	
	public WebElement privacyPolicy()
	{
		return privacyPolicy;
	}
	
	public WebElement continuebutton()
	{
		return continuebutton;
	}
	
	public String warningMsg()
	{
		return WarningMsg.getText();
	}

}
