package DipaliBhavsarSelenium.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DipaliBhavsarSelenium.AbstractComponant.AbstractComponant;


public class CartPage extends AbstractComponant {
	WebDriver driver;
	public CartPage(WebDriver driver)
	{   //initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	} 
	
	@FindBy(css=".totalRow button")   
	WebElement CheckoutEle;
	
	//PageFactory
	@FindBy(xpath="//*[@class='cartSection']/h3")   
	List<WebElement> cartproducts;
	
	public boolean verifyProductDisplay(String Productname) {
		boolean match = cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(Productname));
		return match;
	}
	
	public CheckoutPage  goToCheckout(String a) throws InterruptedException {
		WindowScroller(a);
		//waitForElementVisible(CheckoutEle);
		CheckoutEle.click();
		
		return new CheckoutPage(driver);
		
	}
	
	
	
}
