package tutorialNinjaProject.QA.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import tutorialNinjaProject.QA.Utlis.Utilities;

public class BaseClass{
	
	WebDriver d;
	public Properties prop ;
	public Properties dataprop;
	public  BaseClass() throws Exception
	{
		prop = new Properties();
		File propfile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\tutorialNinjaProject\\QA\\Config\\Config.properities");
		FileInputStream fis = new FileInputStream(propfile);
		prop.load(fis);
		
		/*dataprop = new Properties();
		File file1 = new File("/TutorialNinjaProject/src/main/java/tutorialNinjaProject/QA/Testdata/Testdata.properties");
		FileInputStream fis1 = new FileInputStream(file1);
		dataprop.load(fis1);*/
	}
	
	public WebDriver  intiliazeBrowser(String browserName)
	{
		if(browserName.equals("Chrome"))
		{
			d = new ChromeDriver();
		}
		else if(browserName.equals("edge"))
		{
			d = new EdgeDriver();
		}
		
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.implicitWaitTime));
		d.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.pageloadTime));
		d.get(prop.getProperty("url"));
		
		return d;
		
	}

}
