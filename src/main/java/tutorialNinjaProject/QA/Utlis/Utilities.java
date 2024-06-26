package tutorialNinjaProject.QA.Utlis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utilities 
{
	
	public static final int implicitWaitTime = 10;
	public static final int pageloadTime = 5;
	public static String generateEmailWithTimeStamp()
	{
		Date date = new Date();
		String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return timeStamp;
	}
	
	public static Object[][] getTestDataFromExcelFile() throws Exception
	{
		File file = new File("C:\\Users\\srika\\Downloads\\TestData.xlsx");
		System.out.println(file.exists());
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		
		int rowcount =sheet.getLastRowNum();
		int colcount = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rowcount][colcount];
		for(int r=0;r<rowcount;r++)
		{
			XSSFRow row =sheet.getRow(r+1);
			for(int c=0;c<colcount;c++)
			{
				XSSFCell cell=row.getCell(c);
			
				
				switch(cell.getCellType())
				{
				case STRING:
					data[r][c]= cell.getStringCellValue();
				case NUMERIC:
					data[r][c]= cell.getNumericCellValue();
				case BOOLEAN:
					data[r][c]=cell.getBooleanCellValue();
				}
			}
		}
		return data;

	}
	

}
