package Test_Package;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.asprise.util.ocr.OCR;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Image {
	
	WebDriver driver;
	
	@BeforeTest
	public void setup() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		
	}
	
	@Test
	 public void start() throws IOException, InterruptedException{
	   
	 /*Navigate to http://www.mythoughts.co.in/2013/10/extract-and-verify-text-from-image.html page
	  * and get the image source attribute
	  *  
	  */  
	 driver.get("http://www.mythoughts.co.in/2013/10/extract-and-verify-text-from-image.html");
	 Thread.sleep(8000);
	 //String imageUrl=driver.findElement(By.xpath("/*[@id='post-body-5614451749129773593']/div[1]/div[1]/div/a/img/")).getAttribute("src");
	  WebElement imageUrl = driver.findElement(By.xpath("//img[@src='http://2.bp.blogspot.com/-42SgMHAeF8U/Uk8QlYCoy-I/AAAAAAAADSA/TTAVAAgDhio/s1600/love.jpg']"));
	  String imageUrl1 = imageUrl.getAttribute("src");
	
	 
	 System.out.println("Image source path : \n"+ imageUrl1);
	 
	 URL url = new URL(imageUrl1);
	 BufferedImage image = ImageIO.read(url);
	String s = new OCR().recognizeCharacters((RenderedImage) image);  
	
	 
	 System.out.println("Text From Image : \n"+ s);
	 System.out.println("Length of total text : \n"+ s.length());
	 driver.quit();
	    
	 /* Use below code If you want to read image location from your hard disk   
	  *   
	   BufferedImage image = ImageIO.read(new File("Image location"));   
	   String imageText = new OCR().recognizeCharacters((RenderedImage) image);  
	   System.out.println("Text From Image : \n"+ imageText);  
	   System.out.println("Length of total text : \n"+ imageText.length());   
	      
	   */ 
	
}
}