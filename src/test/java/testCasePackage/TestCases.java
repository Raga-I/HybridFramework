package testCasePackage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import basePackage.BaseClass;
import pagesCreation.CartPage;
import pagesCreation.CheckoutPage;
import pagesCreation.OrderPage;
import pagesCreation.ProductPage;

//Initialize webdriver
//invoke browser
//create object for classes from which methods are invoke.

public class TestCases extends BaseClass {
	//String productName = "ZARA COAT 3";
	//String countryName = "India";

	@Test(dataProvider="getData",groups= {"Smoke"})
	public void orderProduct(HashMap<String,String> input) throws IOException, InterruptedException {
		ProductPage pp = lp.landingPageActions(input.get("emailId"), input.get("password"));
		CartPage cp = pp.productPageActions(input.get("productName"));
		Boolean flag = cp.cartPageActions(input.get("productName"));
		Assert.assertTrue(flag);

		CheckoutPage chp = cp.clickCheckoutButton();
		chp.checkoutPageActions("INDIA");
	}

	// connect two test cases by depends on methods attribute
	/*@Test(dependsOnMethods = { "orderProduct" })
	public void checkProductInOrderHeader() throws InterruptedException {
		Thread.sleep(2000);
		ProductPage pp = lp.landingPageActions("anshika@gmail.com", "Iamking@000");
		OrderPage op = pp.orderHeader();
		Boolean flag = op.orderPageActions(productName);
		Assert.assertTrue(flag);

	}*/

	/*@Test(dataProvider="getData",groups= {"Smoke"})
	public void orderProduct(String email, String password, String productName) throws IOException, InterruptedException {
		ProductPage pp = lp.landingPageActions(email, password);
		CartPage cp = pp.productPageActions(productName);
		Boolean flag = cp.cartPageActions(productName);
		Assert.assertTrue(flag);

		CheckoutPage chp = cp.clickCheckoutButton();
		chp.checkoutPageActions("INDIA");
	}
	
	  @DataProvider 
	  public Object[][] getData() { 
		  return new Object[][] {
			  {"anshika@gmail.com","Iamking@000","ZARA COAT 3"},
			  {"rahulshetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}
			  };}*/
	 

	/*@Test(dataProvider="getData",groups= {"Smoke"})
	public void orderProduct(HashMap<String,String> input) throws IOException, InterruptedException {
		ProductPage pp = lp.landingPageActions(input.get("emailId"), input.get("password"));
		CartPage cp = pp.productPageActions(input.get("productName"));
		Boolean flag = cp.cartPageActions(input.get("productName"));
		Assert.assertTrue(flag);

		CheckoutPage chp = cp.clickCheckoutButton();
		chp.checkoutPageActions("INDIA");
	}

	@DataProvider
	public Object[][] getData() {
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("emailId", "anshika@gmail.com");
		map1.put("password", "Iamking@000");
		map1.put("productName", "ZARA COAT 3");

		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("emailId", "rahulshetty@gmail.com");
		map2.put("password", "Iamking@000");
		map2.put("productName", "ADIDAS ORIGINAL");
		return new Object[][] { { map1 }, { map2 } };
	}*/
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> tData = getDataFromJson(System.getProperty("user.dir")+"//src//main//java//resources//TestData.json");
		return new Object[][] { { tData.get(0) }, { tData.get(1) } };
	}

}
