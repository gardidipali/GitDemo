package DipaliBhavsarSelenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DipaliBhavsarSelenium.AbstractComponant.AbstractComponant;

public class ConfirmationPage extends AbstractComponant {
		WebDriver driver;
		public ConfirmationPage(WebDriver driver)
		{   //initialization
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(css=".hero-primary")
		WebElement confirmationMassge;
		
		public String verifyConfirmationMassge(String a) throws InterruptedException {
			WindowScroller(a);
			return confirmationMassge.getText();
		}
}
