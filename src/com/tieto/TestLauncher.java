package com.tieto;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.flipkart.pages.FlipkartLoginPage;
import com.tieto.frameworkServices.core.FrameworkServices;
import com.tieto.frameworkServices.core.WebPage;



public class TestLauncher{
	public static WebDriver driver;
	
	@Parameters({"browsername","browserversion","platform"})
	public void StartExecution(String browsername,String browserversion,String platform  ) {
		FrameworkServices fs= new FrameworkServices();
		try {
			driver= fs.getWebDriverInstance(browsername,browserversion,platform);
			
			FlipkartLoginPage amazonLoginPage = new FlipkartLoginPage(driver);
			
			amazonLoginPage.FillLoginDetailsAmazon();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			driver.quit();
		}
	
	}
	
	
}
