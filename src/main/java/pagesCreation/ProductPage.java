package pagesCreation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reusableMethods.ReuseMethods;

public class ProductPage extends ReuseMethods {
	WebDriver driver;

	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> productsList;

	@FindBy(css = ".ng-animating")
	WebElement toastMessageDisappear;

	By waitForProducts = By.cssSelector(".mb-3");
	By selectActualProduct = By.cssSelector(".card-body button:last-of-type");
	By toastMessageAppear = By.cssSelector("#toast-container");

	public CartPage productPageActions(String productName) throws InterruptedException {
		explicitWait_visibilityOfElement(waitForProducts);
		WebElement productSelect = productsList.stream()
				.filter(p -> p.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		productSelect.findElement(selectActualProduct).click();
		explicitWait_visibilityOfElement(toastMessageAppear);
		explicitWait_invisibilityOfElement(toastMessageDisappear);
		cartHeader();
		CartPage cp = new CartPage(driver);
		return cp;
	}

}
