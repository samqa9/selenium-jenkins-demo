package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {
	// Browser driver
	
	private final WebDriver driver;
	private final WebDriverWait wait;
	
	// Locators
	// Locators: Identify the username, password, login button, and message.
	private final By username=By.id("username");
	private final By password=By.id("password");
	
	private final By loginButton=By.cssSelector("button[type='submit']");
	
	private final By flashMessage=By.id("flash");

	// Actions - WebDriver Initialization
		  public LoginPage(WebDriver driver) {
	        this.driver = driver;
	        // Explicit waits: Wait for elements to become visible or clickable.
	        this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	    }
	
	  // Open Login page by web driver 
	  	  public void openLoginPage() {
		  	driver.get("https://the-internet.herokuapp.com/login");		
	  	}
	  	  
	  // Login page displayed or not
	  	 public boolean isLoginFormDisplayed() {
	         return wait.until(
	             ExpectedConditions.visibilityOfElementLocated(username)
	         ).isDisplayed();
	     }
	  
	  // Enter User name
	  	  public void enterUsername(String value) {
	  		  WebElement element=wait
	  				  .until(ExpectedConditions.visibilityOfElementLocated(username));
	  		  element.clear();
	  		  element.sendKeys(value);
	  	  }
	  // Enter password
	  	  public void enterPassword(String value) {
	  		  WebElement element=wait
	  				  .until(ExpectedConditions.visibilityOfElementLocated(password));
	  		  element.clear();
	  		  element.sendKeys(value);
	  	  }
	  	  
	  // Click Login
	  	 public void clickLogin() {
	        wait.until(
	            ExpectedConditions.elementToBeClickable(loginButton)
	        ).click();
	    }
	  	  
	  // Get Flash Message
	  	  public String getFlashmessage() {
	  		  return wait.until(ExpectedConditions.visibilityOfElementLocated(flashMessage)).getText();
	  	  }
	  	  
	  	  
	  public void login(String user,String pass) {
		  enterUsername(user);
		  enterPassword(pass);
		  clickLogin();
	  }
}
