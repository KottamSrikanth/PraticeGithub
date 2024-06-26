package tutorialsNinja.Qa.TestCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import tutorialNinjaProject.QA.Base.BaseClass;
import tutorialNinjaProject.QA.Utlis.Utilities;
import tutorialNinjaProject.QA.pageObjectModel.MyAccount;
import tutorialNinjaProject.QA.pageObjectModel.Regristerpage;

public class Regrister extends BaseClass{
	
	public Regrister() throws Exception 
	{
		super();
	}
	
	public WebDriver d ;
	Regristerpage rp;
	@BeforeMethod
	public void setUp ()
	{
		
		d = intiliazeBrowser(prop.getProperty("browser"));
		MyAccount my = new MyAccount(d);
		my.myAccount().click();
		my.Register().click();
				
	}
	@AfterMethod
	public void teardown()
	{
		d.quit();
	}
	@Test
	public void regrsiteronlyMandatoryfields() throws Exception
	{
	
		rp = new Regristerpage(d);
		rp.firstName().sendKeys("Srikanth");
		rp.lastName().sendKeys("yadav");
		rp.email().sendKeys("srikanth"+Utilities.generateEmailWithTimeStamp()+"@gmail.com");
		rp.telephone().sendKeys("1234563");
		rp.password().sendKeys("1234567");
		rp.passwordConfirm().sendKeys("1234567");
		rp.privacyPolicy().click();
		rp.continuebutton().click();
		
		String actualResult = d.findElement(By.xpath("//div[@id='content']//h1")).getText();
		String expectedResult = "Your Account Has Been Created!";
		Assert.assertEquals(actualResult, expectedResult,"The Regrister is not done");
		Thread.sleep(2000);
		

	}
	@Test
	public void regristerWithoutprovidinganydetails() throws Exception
	{
	
		d.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualWarning = d.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedResult = rp.warningMsg();
		Assert.assertEquals(actualWarning, expectedResult,"Warning msg is incorrect");
		Thread.sleep(2000);
		
	}
	
	@Test
	public void verifyDifferentpasswords()
	{
		d.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Srikanth");
		d.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("yadav");
		d.findElement(By.xpath("//input[@id='input-email']")).sendKeys(""+Utilities.generateEmailWithTimeStamp()+"@gamil.com");
		d.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("123456789");
		d.findElement(By.xpath("//input[@id='input-password']")).sendKeys("123456");
		d.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("98765");
		d.findElement(By.xpath("//input[@name='agree']")).click();
		d.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualresult = d.findElement(By.xpath("//div[@class='col-sm-10']//div[@class='text-danger']")).getText();
		String expectedResult = "Password confirmation does not match password!";
		Assert.assertTrue(actualresult.contains(expectedResult));
		
	}
	
	@Test
	public void givingSpacesintothefileds()
	{
		d.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("   ");
		d.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("  ");
		d.findElement(By.xpath("//input[@id='input-email']")).sendKeys(""+Utilities.generateEmailWithTimeStamp()+"@gamil.com");
		d.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("  ");
		d.findElement(By.xpath("//input[@id='input-password']")).sendKeys("  ");
		d.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("  ");
		d.findElement(By.xpath("//input[@name='agree']")).click();
		d.findElement(By.xpath("//input[@value='Continue']")).click();
		
		
	}
}
