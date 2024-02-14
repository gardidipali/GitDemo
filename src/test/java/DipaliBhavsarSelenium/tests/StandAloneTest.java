package DipaliBhavsarSelenium.tests;



import java.io.File;
import java.io.IOException;import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import DipaliBhavsarSelenium.TestComponant.BaseTest;
import DipaliBhavsarSelenium.pageobjects.CartPage;
import DipaliBhavsarSelenium.pageobjects.CheckoutPage;
import DipaliBhavsarSelenium.pageobjects.ConfirmationPage;
import DipaliBhavsarSelenium.pageobjects.Landingpage;
import DipaliBhavsarSelenium.pageobjects.OrderPage;
import DipaliBhavsarSelenium.pageobjects.ProductCatalog;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest extends BaseTest {
	String Productname="ZARA COAT 3";
	@Test(dataProvider="getdata",groups= {"Purches"})
	public void SubmitOrder(HashMap<String,String>input) throws IOException, InterruptedException {
	//public void SubmitOrder(String Email,String Password, String Productname) throws IOException, InterruptedException {
	
		
		//JavascriptExecutor js=  (JavascriptExecutor)driver;
		//login into application
		ProductCatalog productCatalog=landingpage.LoginApplication(input.get("Email"),input.get( "Password"));
		// get list of product and click on it
		//ProductCatalog productCatalog= new ProductCatalog(driver);
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(input.get("Product"));
		CartPage cartPage=productCatalog.goToCartPage();		
		//vallidate in cartlist product is present or not
		//CartPage cartPage = new CartPage(driver);
		boolean match = cartPage.verifyProductDisplay(input.get("Product"));
		Assert.assertTrue(match);
		//toast-container
		String scroll="window.scrollBy(0,100)";
		CheckoutPage CheckoutPage=cartPage.goToCheckout(scroll);
		//select country and place order
		//using Actions class
		CheckoutPage.selectCountry("India");
		ConfirmationPage ConfirmationPage =CheckoutPage.submitOrder(scroll);
		//vallidate with success massage
		//js.executeScript("window.scrollBy(0,-100)");
		String confirm=ConfirmationPage.verifyConfirmationMassge("window.scrollBy(0,-100)");
	Assert.assertTrue(confirm.equalsIgnoreCase("THANKYOU FOR THE ORDER."));	
	
	}
	
	@Test(dependsOnMethods={"SubmitOrder"})
	public void OrderHistoryTest() {
		ProductCatalog productCatalog=landingpage.LoginApplication("gardidipali1@gmail.com", "Dipali#1bhavsar");
		OrderPage orderPage = productCatalog.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(Productname));
		
	}
	
	
	
	
	
//	@DataProvider
//	public Object[][] getdata() {
//		return new Object[][] {{"gardidipali1@gmail.com", "Dipali#1bhavsar","ZARA COAT 3"},{"gardidipali1@gmail.com", "Dipali#1bhavsar","ADIDAS ORIGINAL"}};
//		}
	
	@DataProvider
	public Object[][] getdata() throws IOException {
//		HashMap<String,String> map=new HashMap<String,String>();
//		map.put("Email", "gardidipali1@gmail.com");
//		map.put("Password", "Dipali#1bhavsar");
//		map.put("Product", "ZARA COAT 3");
//		
//		HashMap<String,String> map1=new HashMap<String,String>();
//		map1.put("Email", "gardidipali1@gmail.com");
//		map1.put("Password", "Dipali#1bhavsar");
//		map1.put("Product", "ADIDAS ORIGINAL");
		List<HashMap<String,String>>data =getJsonDataToMap("D:\\Dipali cources\\My_selenium_data\\SeleniumFrameworkDesign\\src\\test\\java\\DipaliBhavsarSelenium\\Data\\PurchesOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
		//return new Object[][] {{map},{map1}};
		}

}
