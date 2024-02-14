package DipaliBhavsarSelenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import DipaliBhavsarSelenium.AbstractComponant.AbstractComponant;

public class CheckoutPage extends AbstractComponant {
	WebDriver driver;
	public CheckoutPage(WebDriver driver)
	{   //initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@placeholder='Select Country']")   
	WebElement Country;
	//a.sendKeys(driver.findElement(By.xpath("//*[@placeholder='Select Country']")), "India").build().perform();
	
	@FindBy(css=".action__submit")   
	WebElement submit;
	//driver.findElement(By.cssSelector(".action__submit")).click();
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")   
	WebElement selectCountry;
	//driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
	
	By result =By.cssSelector(".ta-results");
	
	public void selectCountry(String countryname) {
		
		Actions a= new Actions(driver);
		a.sendKeys(Country, countryname).build().perform();
		waitForElementToAppear(result);
		selectCountry.click();
		
	}
	
	public ConfirmationPage submitOrder(String a) throws InterruptedException {
		WindowScroller(a);
		submit.click();
		return new ConfirmationPage(driver);
	}
	
}
