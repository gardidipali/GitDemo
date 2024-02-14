package DipaliBhavsarSelenium.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DipaliBhavsarSelenium.AbstractComponant.AbstractComponant;


public class OrderPage extends AbstractComponant {
	WebDriver driver;
	public OrderPage(WebDriver driver)
	{   //initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	} 
	
	
	//PageFactory
	@FindBy(xpath="//*[@class='cartSection']/h3")   
	List<WebElement> cartproducts;
	
	@FindBy(css="tr td:nth-child(3)")   
	List<WebElement>productsNames;
	
	public boolean verifyOrderDisplay(String Productname) {
		boolean match = productsNames.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(Productname));
		return match;
	}
	
	
	
	
}
