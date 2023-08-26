package reusableMethods;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pagesCreation.CartPage;
import pagesCreation.OrderPage;

//initialize webdriver
//create param constructor
//create reusable methods

public class ReuseMethods {

	WebDriver driver;
	public ReuseMethods(WebDriver driver) {
		this.driver = driver;
		
	}

	@FindBy(css="[routerlink*='cart']")
	WebElement cart;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement header;
	
	
	public void maximize() {
		driver.manage().window().maximize();
	}

	public void explicitWait_visibilityOfElement(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void explicitWait_visibilityOfElement(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void explicitWait_invisibilityOfElement(WebElement toastMessageDisappear) throws InterruptedException {
		Thread.sleep(1000);
		/*WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait1.until(ExpectedConditions.invisibilityOf(toastMessageDisappear));*/
	}
	
	public CartPage cartHeader()
	{
		cart.click();
		CartPage cp = new CartPage(driver);
		return cp;
	}
	
	public OrderPage orderHeader()
	{
		header.click();
		OrderPage op = new OrderPage(driver);
		return op;
	}
}
