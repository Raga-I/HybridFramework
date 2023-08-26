package pagesCreation;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusableMethods.ReuseMethods;

public class OrderPage extends ReuseMethods {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//tr[@class='ng-star-inserted']/td[2]")
	List<WebElement> ordersName;

	public Boolean orderPageActions(String orderText) {
		OrderPage op= orderHeader();
		Boolean flag1 = ordersName.stream().anyMatch(name -> name.getText().equalsIgnoreCase(orderText));
		return flag1;
	}
}
