package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LoginPage;

public class LoginTest {
	// Initialize
	private WebDriver driver;
	private LoginPage loginPage;
	
	//  1.TestNG executes @BeforeMethod.
	//	2.Selenium starts Chrome.
	//	3.The browser opens the login page.	
	
	@BeforeMethod
	public void setUp() {
		ChromeOptions options=new ChromeOptions();
		
		// Open headless and maximum resolution
		// Jenkins can execute Chrome without a visible window.
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false")
        );
        if (headless) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1920,1080");
	
        // Use the class objects
			driver=new ChromeDriver(options);
//			Or
//			WebDriver driver=new ChromeDriver();
			loginPage=new LoginPage(driver);
			loginPage.openLoginPage();
	
	}
	
	//	5.TestNG evaluates the assertion.
	  @Test
	    public void verifyLoginPageIsDisplayed() {

	        Assert.assertTrue(
	                loginPage.isLoginFormDisplayed(),
	                "Login form should be displayed"
	        );
	  }
	  
	  public void verifyValidLogin() {
		  	loginPage.login(
				  "tomsmith",
	                "SuperSecretPassword!");
		  	
		  	Assert.assertTrue(
		  			loginPage.getFlashmessage().contains("You logged into a secure area!"),
		  			"Successful login message should be displayed");
	  }
	  
	  public void verifyInvalidUsername() {
		  	loginPage.login(
				  "tomsmith123",
	                "SuperSecretPassword!");
		  	
		  	Assert.assertTrue(
		  			loginPage.getFlashmessage().contains("Your username is invalid"),
		  			"Invalid username message should be displayed");
	  }
	  
	  
	  public void verifyInvalidPassword() {
		  	loginPage.login(
				  "tomsmith123",
	                "SecretPassword!");
		  	
		  	Assert.assertTrue(
		  			loginPage.getFlashmessage().contains("Your password is invalid"),
		  			"Invalid password message should be displayed");
	  }
	  
//	  6.@AfterMethod closes the browser.
	  @AfterMethod
	  public void tearDown() {
		  if(driver!=null) {
			  driver.quit();
		  }
	  }
}
