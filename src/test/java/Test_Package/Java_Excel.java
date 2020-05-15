package Test_Package;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Java_Excel {

	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		System.out.println(readXL());
		
		retriveData(readXL());				
	}
	public static List<Map<String, String>> readXL() throws IOException  {
			
			
			File myxl = new File("C:\\Users\\JACKSON\\eclipse-workspace\\Data_Driven_Framework_01\\Test-Data\\LoginDetails.xls");
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

	
		public static void retriveData(List<Map<String, String>> readXL) {
			
					
			for(Map<String, String> map : readXL){
				
				map.get("userName");
	               
				for(Map.Entry ent : map.entrySet()) {
					
				System.out.println(ent.getKey() + " --- "+ ent.getValue());
//				System.out.println("Single Map entry userName" + map.get("UserName"));
//				System.out.println("Single Map entry password" +map.get("Password"));	
		}
		
			}



}
}