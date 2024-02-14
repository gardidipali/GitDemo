package DipaliBhavsarSelenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DipaliBhavsarSelenium.AbstractComponant.AbstractComponant;


public class Landingpage extends AbstractComponant {
	WebDriver driver;
	public Landingpage(WebDriver driver)
	{   //initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	} 
	
	
	//WebElement UserEmails=driver.findElement(By.id("userEmail"));
	
	//PageFactory
	@FindBy(id="userEmail")   // this is alternate for lin no. 18
	WebElement UserEmail;
	
	@FindBy(id="userPassword")   
	WebElement PasswordEle;
	
	@FindBy(name="login")   
	WebElement Submit;
	
	@FindBy(css="[class*='flyInOut']")   
	WebElement errorMassage;
	
	public ProductCatalog LoginApplication(String Email, String Password) 
	{
		 UserEmail.sendKeys(Email);
		 PasswordEle.sendKeys(Password);
		 Submit.click();
		 
		 ProductCatalog productCatalog= new ProductCatalog(driver);
		 return productCatalog;
	}
	
	public String getErrorMassage() {
		waitForElementVisible(errorMassage);
		return	errorMassage.getText();
		
		}
	
	
   public void Goto()
   {
	   driver.get("https://rahulshettyacademy.com/client");   
   }
	
}
