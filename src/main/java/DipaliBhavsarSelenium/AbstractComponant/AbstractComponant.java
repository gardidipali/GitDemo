package DipaliBhavsarSelenium.AbstractComponant;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import DipaliBhavsarSelenium.pageobjects.CartPage;
import DipaliBhavsarSelenium.pageobjects.OrderPage;

public class AbstractComponant {
	WebDriver driver;
	public AbstractComponant(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(css="[routerlink*='cart']")   
	WebElement CartHeader;
	
	@FindBy(css="[routerlink*='myorders']")   
	WebElement orderHeader;
	
   public void waitForElementToAppear(By findby) {
	   WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
	  wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	 
}
   
   public void waitForElementVisible(WebElement a) {
	   WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
	   wait.until(ExpectedConditions.visibilityOf(a));
   }
	
   public void WindowScroller(String scrollby) throws InterruptedException {
	   JavascriptExecutor js= (JavascriptExecutor)driver;
	   js.executeScript(scrollby);
	   Thread.sleep(3000);
	 
}
   
	public void waitForElementToDissapear(WebElement ele) throws InterruptedException {
		
		Thread.sleep(1000);
		/*
		 * WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		 * wait.until(ExpectedConditions.invisibilityOf(ele));
		 */
	}
	
	
	
	public CartPage goToCartPage() {
		CartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	public OrderPage goToOrdersPage() {
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
	

}