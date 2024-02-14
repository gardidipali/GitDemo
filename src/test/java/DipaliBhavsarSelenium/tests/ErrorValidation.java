package DipaliBhavsarSelenium.tests;



import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import DipaliBhavsarSelenium.TestComponant.BaseTest;
import DipaliBhavsarSelenium.pageobjects.CartPage;
import DipaliBhavsarSelenium.pageobjects.CheckoutPage;
import DipaliBhavsarSelenium.pageobjects.ConfirmationPage;
import DipaliBhavsarSelenium.pageobjects.ProductCatalog;

public class ErrorValidation extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer= DipaliBhavsarSelenium.TestComponant.Retry.class  )
	public void LogineErrorValidation() throws IOException, InterruptedException {
		
		landingpage.LoginApplication("gardidipali4@gmail.com", "Dipali@1bhavsar");
		System.out.println(landingpage.getErrorMassage());
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMassage());
		
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String Productname="ZARA COAT 3";
		
		ProductCatalog productCatalog=landingpage.LoginApplication("gardidipali1@gmail.com", "Dipali#1bhavsar");
		
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(Productname);
		CartPage cartPage=productCatalog.goToCartPage();		
	
		boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
	 }

}
