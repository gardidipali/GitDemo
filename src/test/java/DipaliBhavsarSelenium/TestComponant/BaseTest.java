package DipaliBhavsarSelenium.TestComponant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import DipaliBhavsarSelenium.pageobjects.Landingpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public Landingpage landingpage;
	
	public WebDriver InitializeDriver() throws IOException {
		
		//properties
		Properties prop = new  Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\DipaliBhavsarSelenium\\resources\\GlobleData.properties");
		prop.load(fis);
		
		String BrowserName= prop.getProperty("Browser");
		
		if(BrowserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
		 driver =new ChromeDriver();
		
		}
		else if(BrowserName.equalsIgnoreCase("firefox")) {
			//firefox
			 driver =new ChromeDriver();
			 driver =new ChromeDriver();
				
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	@BeforeMethod(alwaysRun= true)
	public Landingpage LaunchApplication() throws IOException {
		
		driver=InitializeDriver();
		 landingpage= new Landingpage(driver);
		landingpage.Goto();
		return landingpage;
	}
	
	@AfterMethod(alwaysRun= true)
	public void TearDown() {
		driver.close();
	}
	
	public List<HashMap<String,String>> getJsonDataToMap(String filepath) throws IOException{
		//resd json to string
		String jsonContent= FileUtils.readFileToString(new File(filepath),
				StandardCharsets.UTF_8);
	
	
		//string to Hashmap jackson databind
		
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data= mapper.readValue(jsonContent,new TypeReference <List<HashMap<String,String>>>(){  
		});
		return data;
		}
	
    public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		
		TakesScreenshot ts	=(TakesScreenshot)driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		
		File file= new File("C:\\Users\\10558\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\test\\java\\DipaliBhavsarSelenium\\Report\\"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return ("C:\\Users\\10558\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\test\\java\\DipaliBhavsarSelenium\\Report\\"+testCaseName+".png");
	}
	
	
}
