package tutorialsNinja.Qa.TestCases;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import tutorialNinjaProject.QA.Base.BaseClass;
import tutorialNinjaProject.QA.Utlis.Utilities;
import tutorialNinjaProject.QA.pageObjectModel.LoginPage;
import tutorialNinjaProject.QA.pageObjectModel.MyAccount;

public class Login extends BaseClass{
	
	public WebDriver d;
	public Login() throws Exception
	{
		super();
	}
	@AfterMethod
	public void Quitbrowser()
	{
		d.quit();
	}
	
	@BeforeMethod
	public void Setup() throws Exception
	{
		
		d =intiliazeBrowser(prop.getProperty("browser"));
		d.findElement(By.xpath("//span[contains(@class,'hidden-md') and text()='My Account']")).click();
		d.findElement(By.linkText("Login")).click();
	}
	@Test(dataProvider =  "login")
	public void verifyLoginwithValidCreditionals(String userName , String pass) throws Exception
	{
		
		MyAccount my = new MyAccount(d);
		my.myAccount().click();
		my.LoginButton().click();
		LoginPage lp = new LoginPage(d);
		lp.userEmail().sendKeys(userName);
		lp.userPassword().sendKeys(pass);
		lp.LoginButton().click();

		Assert.assertTrue(d.findElement(By.linkText("Account")).isDisplayed());
		Thread.sleep(2000);
		
	}
	
	@DataProvider(name="login")
	public Object[][]supplyTestdata()
	{
		Object[][] data = {{"srikanth36@gmail.com","123456"},
				{"srikanth998765@gmail.com","98765"},
				{"srikanth998765@gmail.com","2345"}};
		return data;
	}
	@DataProvider(name ="ValidCreditionalssuppiler")
	public Object[][] supplyTestData() throws Exception
	{
		Object[][] data = Utilities.getTestDataFromExcelFile();
		return data;
	}
	
	@Test
	public void verfiyLoginwithInvalidCreditionals() throws Exception
	{
		
		d.findElement(By.xpath("//input[@name='email']")).sendKeys("Srikanthdav36"+Utilities.generateEmailWithTimeStamp()+"@gmail.com");
		d.findElement(By.xpath("//input[@name='password']")).sendKeys(dataprop.getProperty("invalidPassword"));
		d.findElement(By.xpath("//input[@value='Login']")).click();

		String actual_result=d.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedresult = "Warning: No match for E-Mail Address and/or Password.";

		Assert.assertTrue(actual_result.contains(expectedresult),"Warning message is not coming");
		Thread.sleep(2000);
	
	}

	@Test
	public void verfiywithInvalidEmailandValidPassword() throws Exception
	{

		d.findElement(By.xpath("//input[@id='input-email']")).sendKeys("Srikanth"+Utilities.generateEmailWithTimeStamp()+"@gmail.com");
		d.findElement(By.xpath("//input[@id='input-password']")).sendKeys("123456");
		d.findElement(By.xpath("//input[@value='Login']")).click();

		String actualresult =d.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedResult = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(actualresult, expectedResult);
		Thread.sleep(2000);
	
	}

	@Test
	public void verfiywithoutprovidinganycreditionals() throws InterruptedException
	{
	
		d.findElement(By.xpath("//input[@value='Login']")).click();
		String actualresult = d.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedResult = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualresult.contains(expectedResult));
		
	
	}
	
	@Test(dataProvider = "readexcelvalue")
	public void verfiyloginintoapplicationandbrowsingback(String userName ,String password) throws Exception
	{
		
		d.findElement(By.xpath("//input[@id='input-email']")).sendKeys(userName);
		d.findElement(By.xpath("//input[@id='input-password']")).sendKeys(password);
		d.findElement(By.xpath("//input[@value='Login']")).click();
		d.findElement(By.xpath("//span[text()='My Account']")).click();
		Actions a = new Actions(d);
		a.moveToElement(d.findElement(By.xpath("//ul[contains(@class,'dropdown-menu-right')]//a[text()='Logout']"))).click().perform();
		Thread.sleep(2000);
		d.navigate().back();
		System.out.println(d.findElement(By.linkText("Account")).isDisplayed());
		Assert.assertTrue(d.findElement(By.linkText("Account")).isDisplayed());
		
	}
	
	
}
