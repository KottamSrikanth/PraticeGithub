package tutorialNinjaProject.QA.Utlis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class MyExtentReporter 
{
	public static ExtentReports ExtentReporter() 
	{
		ExtentReports extentreport = new ExtentReports();
		File file = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkReport = new ExtentSparkReporter(file);
		
		sparkReport.config().setTheme(Theme.DARK);
		sparkReport.config().setReportName("TutorialsNinja Test Automation Result");
		sparkReport.config().setDocumentTitle("TN Automation Report");
		sparkReport.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		
		Properties prop = new Properties();
		File file11 = new File(System.getProperty("user.dir")+"\\src\\main\\java\\tutorialNinjaProject\\QA\\Config\\Config.properities");
		FileInputStream propfis;
		try {
			propfis = new FileInputStream(file11);
			prop.load(propfis);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		extentreport.attachReporter(sparkReport);
		extentreport.setSystemInfo("ApplicationUrl", prop.getProperty("url"));
		extentreport.setSystemInfo("browsername", "browser");
		extentreport.setSystemInfo("Username", System.getProperty("user.name"));
		
		return extentreport;
		
		
	}

}
