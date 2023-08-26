package testCasePackage;

import org.testng.Assert;
import org.testng.annotations.Test;

import basePackage.BaseClass;
import pagesCreation.CartPage;
import pagesCreation.CheckoutPage;
import pagesCreation.ProductPage;

public class NegativeTestCase extends BaseClass {
	String productName = "ZARA COAT 3";
	String countryName = "India";
	
	@Test
	public void loginErrorMessageValidation() throws InterruptedException {
		Thread.sleep(2000);
		lp.landingPageActions("rahulshetty@gmail.com", "Iamking657@000");
		Assert.assertEquals(lp.errorText(), "Incorrect email password.");
	}

	@Test
	public void completionPageAssertValidation() throws InterruptedException {
		ProductPage pp = lp.landingPageActions("anshika@gmail.com", "Iamking@000");

		CartPage cp = pp.productPageActions(productName);

		Boolean flag = cp.cartPageActions("ZARA COAT 33");
		Assert.assertFalse(flag);

		CheckoutPage chp = cp.clickCheckoutButton();
		chp.checkoutPageActions(countryName);
	}
}
