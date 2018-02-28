package com.flipkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.tieto.frameworkServices.core.PageElement;
import com.tieto.frameworkServices.core.WaitType;
import com.tieto.frameworkServices.core.WebPage;

public class FlipkartLoginPage extends WebPage{
	
	private PageElement loginLink;
	private PageElement mobile;
	private PageElement password;
	private PageElement mainSearch;
	private PageElement signin;
	private PageElement clickitem;
	private PageElement AddToCart;
	private PageElement login;
	private PageElement placeOrder;
	private PageElement quantity;
	private PageElement conitinue;
	private PageElement conitinueToPayment;
	private PageElement logout;
	private PageElement searchButton;
	
	public  FlipkartLoginPage(WebDriver driver){
		super(driver);
		
		loginLink=new PageElement(By.xpath("//a[contains(text(),'Log In')]"),"FlipkartLoginPage",true,WaitType.WAITFORELEMENTTOBEDISPLAYED,10);
		mobile =new PageElement(By.xpath("//div[@id='container']//*/form/div/input"),"FlipkartMobilelField",true,WaitType.WAITFORELEMENTTOBEDISPLAYED,10);
		password=new PageElement(By.xpath("//div[@id='container']//*/div[2]/input"),"FlipkartPasswordField",true,WaitType.WAITFORELEMENTTOBEDISPLAYED,10);
		signin=new PageElement(By.xpath("//div[@id='container']//*/div[3]/button"),"FlipkartubmitButton",true,WaitType.WAITFORELEMENTTOBEDISPLAYED,10);
		mainSearch=new PageElement(By.xpath("//input[@name='q']"),"A FlipkartMainSearchField",true,WaitType.WAITFORELEMENTTOBEDISPLAYED,10);
		clickitem=new PageElement(By.xpath("//div/div/div/div/a[2]"),"A item in the list",true,WaitType.WAITFORELEMENTTOBEDISPLAYED,10);
		AddToCart=new PageElement(By.xpath("//div[@id='container']//*/li/button"),"AddToCart",true,WaitType.WAITFORELEMENTTOBEDISPLAYED,10);
		quantity=new PageElement(By.xpath("//input[@value='1']"),"Quantity",true,WaitType.WAITFORELEMENTTOBEDISPLAYED,10);
		placeOrder=new PageElement(By.xpath("//form/button"),"placeOrder",true,WaitType.WAITFORELEMENTTOBEDISPLAYED,10);
		conitinue=new PageElement(By.xpath("//button[@type='submit']"),"Continue",true,WaitType.WAITFORELEMENTTOBEDISPLAYED,10);
		searchButton=new PageElement(By.xpath("//button[@type='submit']"),"Continue",true,WaitType.WAITFORELEMENTTOBEDISPLAYED,10);
		conitinueToPayment=new PageElement(By.xpath("//span[@id='to-payment']/button"),"ContinueToPayment",true,WaitType.WAITFORELEMENTTOBEDISPLAYED,10);
		logout=new PageElement(By.xpath("//div[@id='fk-mainhead-id']//*/li[9]/a"),"Logout",true,WaitType.WAITFORELEMENTTOBEDISPLAYED,10);
		
	}
	
	public void FillLoginDetailsAmazon() throws InterruptedException{
		clearAndSendKeys(mainSearch, "Segate 2TB");
		click(searchButton);
		click(clickitem);
		switchToTab();
		click(searchButton);
		click(AddToCart);
		clearAndSendKeys(quantity, "2");
		click(placeOrder);
		clearAndSendKeys(mobile,"8828445658");
		click(conitinue);
		clearAndSendKeys(password, "test@123456");
		click(signin);
		click(logout);
		
		
	}

}
