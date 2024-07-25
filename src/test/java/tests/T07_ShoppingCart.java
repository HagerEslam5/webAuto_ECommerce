package tests;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import bases.RetryFailures;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.P07_Books;
import pages.HomePage;
import pages.P09_cart;

public class T07_ShoppingCart extends BaseTest {

	@Test(priority = 1, retryAnalyzer = RetryFailures.class)
	@Description("Verify that user can add different products to the shopping cart")
	@Severity(SeverityLevel.CRITICAL)

	public void testAddProductsToShoppingCart() throws InterruptedException {
		P07_Books books = new P07_Books(driver);
		List<String> expectedTitles = books.clickOnBooksBox().getAllDisplayedProds();
		int expectedTotalPrice = books.openAllItemsInNewTabs().iterateToAddProductsAndGetTotal();
		books.closeTheDisplayedBar();
		P09_cart cart = new HomePage(driver).goToCart();
		int actualTotalPrice = cart.getTheTotalPrice();
		List<String> ActualTitles = cart.getAllAddedProducts();
		Assert.assertEquals(expectedTotalPrice, actualTotalPrice);
		Assert.assertTrue(expectedTitles.containsAll(ActualTitles));
	}

}
