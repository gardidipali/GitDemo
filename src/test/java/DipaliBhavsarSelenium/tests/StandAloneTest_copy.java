package DipaliBhavsarSelenium.tests;



import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest_copy {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
String Productname="zara coat 3";
		WebDriverManager.chromedriver().setup();
		
		
		WebDriver driver =new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		JavascriptExecutor js=  (JavascriptExecutor)driver;
		//login into application
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("gardidipali1@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Dipali#1bhavsar");
		driver.findElement(By.name("login")).click();
		
		// get list of product and click on it
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("div[class='card-body'] b")).getText().equalsIgnoreCase(Productname)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();		
		
		
		//explicit wait
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//vallidate in cartlist product is present or not
		List<WebElement> cartproducts = driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
		
		boolean match = cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(Productname));
		
		Assert.assertTrue(match);
		//toast-container
		
		js.executeScript("window.scrollBy(0,100)");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//select country and place order
		//using Actions class
		Actions a= new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//*[@placeholder='Select Country']")), "India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		//cssselector=.ta-item:nth-of-type(2)
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		js.executeScript("window.scrollBy(0,100)");
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		//vallidate with success massage
		js.executeScript("window.scrollBy(0,-100)");
		String confirm=driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertTrue(confirm.equalsIgnoreCase(confirm));	
	
	
	
	driver.close();
	}

}
