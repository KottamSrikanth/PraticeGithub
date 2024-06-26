package tutorialsNinja.Qa.TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import tutorialNinjaProject.QA.Base.BaseClass;
import tutorialNinjaProject.QA.pageObjectModel.SearchPage;

public class Search extends BaseClass{
	
	public Search() throws Exception
	{
		super();
	}

	public WebDriver d;
	SearchPage sp;
	@BeforeMethod
	public void setup()
	{
		d =intiliazeBrowser(prop.getProperty("browser"));
		
		
	}
	@AfterMethod
	public void tearDown()
	{
		d.quit();
	}
	@Test
	public void verifySearchWithExistingProductName() throws Exception
	{
		sp = new SearchPage(d);
		sp.searchBox().sendKeys("HP");
		Thread.sleep(2000);
		d.findElement(By.xpath("//div[@id='search']//descendant::button")).click();
		Assert.assertTrue(sp.hplink().isDisplayed());
	}
	@Test
	public void verifySearchWithNonExistingProducts() throws Exception
	{
		SearchPage sp = new SearchPage(d);
		sp.searchBox().sendKeys("Honda");
		d.findElement(By.xpath("//div[@id='search']//descendant::button")).click();
		Thread.sleep(2000);
		String actual_result =d.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		String expectedResult = "There is no product that matches the search criteria.";
		Assert.assertEquals(actual_result, expectedResult,"No product in search result");
	
	}
	
	@Test
	public void verifySearchWithoutgivinganydatainsearchbox() throws Exception
	{
		SearchPage sp = new SearchPage(d);
		sp.searchBox().click();
		Thread.sleep(2000);
		sp.searchBox().sendKeys("");
		Thread.sleep(2000);
		d.findElement(By.xpath("//div[@id='search']//descendant::button")).click();
		Thread.sleep(2000);
		String actual_result =d.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		String expectedResult = "There is no product that matches the search criteria.";
		Assert.assertEquals(actual_result, expectedResult,"No product in search result");
	}

}
