package DipaliBhavsarSelenium.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import DipaliBhavsarSelenium.AbstractComponant.AbstractComponant;


public class ProductCatalog extends AbstractComponant {
	WebDriver driver;
	public ProductCatalog(WebDriver driver)
	{
		
		//initilazation
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	} 
	
	
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	//PageFactory
	@FindBy(css=".mb-3")   // this is alternate for lin no. 18
	List<WebElement>products;
	
	By ProductsBy= By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMassge = By.cssSelector("#toast-container");
	
	@FindBy(css=".ng-animating")
	WebElement Spinner;
	
	public List<WebElement> getProductList() {
		  waitForElementToAppear(ProductsBy);
		  return products;
	}
	
	public WebElement getproductName(String Productname) {
		WebElement prod = getProductList().stream().filter(product->
		product.findElement(By.cssSelector("div[class='card-body'] b")).getText().equalsIgnoreCase(Productname)).findFirst().orElse(null);
		return prod;
	}
	public void addProductToCart(String Productname) throws InterruptedException {
		WebElement prod=getproductName(Productname);
		prod.findElement(addToCart).click();	
		waitForElementToAppear(toastMassge);
		waitForElementToDissapear(Spinner);
		
	}
	
}
