package pagesCreation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reusableMethods.ReuseMethods;

public class CheckoutPage extends ReuseMethods {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement countryName;

	@FindBy(css = ".action__submit")
	WebElement placeOrder;

	By countryList = By.cssSelector(".ta-results");

	public void checkoutPageActions(String typeCountryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, typeCountryName).build().perform();
		explicitWait_visibilityOfElement(countryList);
		countryName.click();
		placeOrder.click();
	}

}
