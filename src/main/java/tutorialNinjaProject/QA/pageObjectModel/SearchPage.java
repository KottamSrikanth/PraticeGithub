package tutorialNinjaProject.QA.pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	WebDriver d;
	public SearchPage(WebDriver d)
	{
		this.d=d;
		PageFactory.initElements(d, this);
	}
	@FindBy(xpath="//input[@name='search']")
	private WebElement searchBox;
	
	
	@FindBy(linkText ="HP LP3065")
	private WebElement Hplink;
	
	public WebElement searchBox()
	{
		return searchBox;
	}
	
	public WebElement hplink()

	{
		return Hplink;
	}
}
