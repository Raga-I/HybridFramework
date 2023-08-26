package pagesCreation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusableMethods.ReuseMethods;
//POM - intilaize webdriver, create param constructor 
//get the url from method
//POM - initialize WebElements + Actions
//WebElements - Write using PageFactory, initElements method
//if no driver, only by locator - write using by 
//write actions for WebElements
//child extends parent - inorder to use parent's method 

public class LandingPage extends ReuseMethods {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement email;

	@FindBy(id = "userPassword")
	WebElement pwd;

	@FindBy(id = "login")
	WebElement lgn;
	
	@FindBy(xpath = "//div[@aria-label='Incorrect email or password.']")
	WebElement errorMessage;

	public void goToUrl() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	//encapsulation concept
	//creating object for productpage class here and returing it.
	//in testcases class, object creation is not required.
	
	public ProductPage landingPageActions(String emailid, String password) {
		
		email.sendKeys(emailid);
		pwd.sendKeys(password);
		lgn.click();
		ProductPage pp = new ProductPage(driver);
		return pp;
		
	}

	public String errorText()
	{
		explicitWait_visibilityOfElement(errorMessage);
		return errorMessage.getText();
	}
}
