package pagesCreation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusableMethods.ReuseMethods;

public class CartPage extends ReuseMethods {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> productsList;

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;

	@FindBy(css = ".totalRow button")
	WebElement checkoutButton;

	By selectProduct = By.xpath("//div[@class='cartSection']/h3");

	public Boolean cartPageActions(String productName) {
		CartPage cp =cartHeader();
		explicitWait_visibilityOfElement(selectProduct);
		Boolean flag = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return flag;
	}

	public CheckoutPage clickCheckoutButton() {

		checkoutButton.click();
		CheckoutPage chp = new CheckoutPage(driver);
		return chp;
	}

}
