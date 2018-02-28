package com.tieto.frameworkServices.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.Parameters;

import com.tieto.frameworkExceptions.FrameworkExecutionException;


public class FrameworkServices {

	static Properties configProp;
	static FileInputStream configInput;

	public static Properties getConfigProperties() throws FileNotFoundException, IOException {
		if (configProp==null) {
			configProp = new Properties();
			configInput = new FileInputStream("config.properties");
			configProp.load(configInput);
		}
		return configProp;
	}



	public static WebDriver getWebDriverInstance(String browsername,String browserversion,String platform) throws FileNotFoundException, IOException, InterruptedException {
		getConfigProperties();
		DesiredCapabilities capability = new DesiredCapabilities();

		if (browsername.equalsIgnoreCase("ie")) {

			File IEDriverPath = new File("\\\\samsung-PC\\Grid\\IEDriverServer.exe");
			System.out.println("IEDriver Path = "+IEDriverPath.getCanonicalPath());
			System.setProperty("webdriver.ie.driver",IEDriverPath.getCanonicalPath());
			capability = DesiredCapabilities.internetExplorer();

		} 
		else if (browsername.equalsIgnoreCase("firefox")) {
			File geckoDriverPath = new File("D:\\Grid\\geckodriver.exe");
			System.setProperty("webdriver.gecko.driver",geckoDriverPath.getAbsolutePath());
			capability = DesiredCapabilities.firefox();
		}
		else if (browsername.equalsIgnoreCase("chrome")) {

			
			File chromeDriverPath = new File("\\\\samsung-PC\\Grid\\chromedriver.exe");

			System.out.println("ChromeDriver Path = "+chromeDriverPath.getCanonicalPath());
			System.setProperty("webdriver.chrome.driver",chromeDriverPath.getCanonicalPath());
			capability = DesiredCapabilities.chrome();
			capability.setCapability("chrome.switches", Arrays.asList("--disable-extensions"));
			capability.setBrowserName("chrome");
			capability.setVersion("58");
			capability.setPlatform(Platform.WINDOWS);;
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-extensions");

			ArrayList<String> excludeSwitches = new ArrayList<String>();
			excludeSwitches.add("ignore-certificate-errors"); 
			options.setExperimentalOption("excludeSwitches", excludeSwitches);		
			capability.setCapability(ChromeOptions.CAPABILITY, options);
		} 

		else {
			throw new FrameworkExecutionException ("Browser Name: " + browsername
					+ " is Not valid Try any one of following \n1 ie ,2 firefox,3 chrome 4 opera");
		}

		String gridURL ="";

		gridURL = getConfigProperties().getProperty("GridURL");



		//WebDriver driver= new FirefoxDriver();
		//Code for execution through grid
		WebDriver driver = new RemoteWebDriver(new URL(gridURL), capability);
		((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		String applicationURL = getConfigProperties().getProperty("FlipKartURL");
		driver.get(applicationURL);
		return driver;
	}

	public void logMessage(String s){
		Reporter.log(s);
	}
}
