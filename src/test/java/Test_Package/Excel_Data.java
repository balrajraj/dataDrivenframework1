package Test_Package;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.Get;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;



public class Excel_Data {
	public static Properties prop;
	private static WebDriver driver; 	
	static String xlPath;
	String xlpath_Result;
	static List<Map<String, String>> xldata; // Define a 2D string
	int xRows, xCols;
	static int xRowcount;
	static String userName;
	static String password;
	static String xlData;
	@BeforeTest
	
	public static void initalization() throws IOException {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://ui.cogmento.com/");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		xlPath = "C:\\Users\\JACKSON\\eclipse-workspace\\Data_Driven_Framework_01\\Test-Data\\LoginDetails.xls";
	
//		File file = new File("C:\\Users\\JACKSON\\eclipse-workspace\\Data_Driven_Framework_01\\src\\main\\java\\Properties_file\\Properties");
//		FileInputStream myFile = new FileInputStream(file);
//		prop = new Properties();
//		
//	     prop.load(myFile);
		 
	     
	     
//	   	retriveData(readXL());
		
			}

//@Test(priority=0)
//	
//public static void retriveData(List<Map<String, String>> readXL)  throws IOException {
//	//public static void retriveData(List<Map<String, String>> readXL) {
//		
//	
//		for(Map<String, String> map : readXL){
//		
//		   		
//	    driver.findElement(By.name("email")).clear();
//		driver.findElement(By.name("email")).sendKeys(map.get("UserName"));
//		driver.findElement(By.name("password")).clear();
//		driver.findElement(By.name("password")).sendKeys(map.get("Password"));
//		
////		prop. (userName);
//	}
//	}
	
@Test
public static void login() throws IOException {
	
	for(Map<String , String> en : readXL()) {
	
driver.findElement(By.name("email")).clear();
driver.findElement(By.name("email")).sendKeys(en.get("UserName"));
driver.findElement(By.name("password")).clear();
driver.findElement(By.name("password")).sendKeys(en.get("Password"));
}
}
	@AfterTest
	
	public static void quit() {
		
		driver.quit();
	}
	public static  List<Map<String, String>> readXL() throws IOException {
		
		
		File myxl = new File(xlPath);
		FileInputStream fis = new FileInputStream(myxl);
		HSSFWorkbook myWB = new HSSFWorkbook(fis);
		HSSFSheet mySheet = myWB.getSheet("Sheet1");
		int xRows = mySheet.getLastRowNum()+1;
		int xCols = mySheet.getRow(0).getLastCellNum();
		System.out.println("Total Rows in Excel are " + xRows);
		System.out.println("Total Cols in Excel are " + xCols);
		
		List<Map<String , String >> mapList = new ArrayList<Map<String, String>>();
		
		for(int j=1;j<xCols;j++) {
			
			Map<String , String > myMap = new HashMap<String, String>();
			
			for(int i =1;i<xRows;i++) {
				
				Row r = CellUtil.getRow(i, mySheet);
				String key = CellUtil.getCell(r, 0).getStringCellValue();
				String value = CellUtil.getCell(r, j).getStringCellValue();
				
				myMap.put(key, value);
			}
			
			mapList.add(myMap);
		}
		
		
		return mapList;			
		} 

//public static String getValue(String keys) throws IOException {
//	
//	Map<String, String> myValue = readXL().get(0);
//	
//	String retValue = myValue.get(keys);
//	
//	
//	return retValue;
//	
//}
	
}
	