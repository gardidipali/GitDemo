package DipaliBhavsarSelenium.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import DipaliBhavsarSelenium.TestComponant.BaseTest;
import DipaliBhavsarSelenium.pageobjects.CartPage;
import DipaliBhavsarSelenium.pageobjects.CheckoutPage;
import DipaliBhavsarSelenium.pageobjects.ConfirmationPage;
import DipaliBhavsarSelenium.pageobjects.Landingpage;
import DipaliBhavsarSelenium.pageobjects.ProductCatalog;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImpl extends BaseTest {
 
	public Landingpage landingpage;
	public ProductCatalog productCatalog;
	public ConfirmationPage ConfirmationPage;
	
	@Given ("I landed on Ecommerce page")
	public void i_landed_on_Ecommerce_page() throws IOException{
		landingpage=LaunchApplication();
	}
	
	@Given ("^Logged in with username(.+) and password(.+)$")
	public void logged_in_username_and_password(String username, String password){
	
	 productCatalog=landingpage.LoginApplication(username,password);
	}
	
	
	
	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
		
	}
	@When("^Checkout(.+)and submit the order$")
	public void  checkout_submit_the_order(String productName) throws InterruptedException {
		CartPage cartPage=productCatalog.goToCartPage();
		
		boolean match = cartPage.verifyProductDisplay(productName);
		//Assert.assertTrue(match);
		
		String scroll="window.scrollBy(0,100)";
		CheckoutPage CheckoutPage=cartPage.goToCheckout(scroll);
		CheckoutPage.selectCountry("India");
		ConfirmationPage =CheckoutPage.submitOrder(scroll);

	}
	
	@Then("{string} massage is displayed on ConfirmationPage")
	public void massage_is_displayed_on_ConfirmationPage(String string) throws InterruptedException {
		
		String confirm=ConfirmationPage.verifyConfirmationMassge("window.scrollBy(0,-100)");
		Assert.assertTrue(confirm.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("^\"([^\"]*)\" massage is displayed$")
		public void somthing_massage_is_displayed(String strArg1) throws Throwable {
		Assert.assertEquals(strArg1, landingpage.getErrorMassage());
		driver.close();
	}
	
	
	

}
