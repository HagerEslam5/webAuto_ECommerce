package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import bases.RetryFailures;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.HomePage;
import pages.P05_Search;
import pages.P10_wishList;

public class T09_WishList extends BaseTest {
	String prodName = "HTC One M8 Android L 5.0 Lollipop";
	String SuccMessg = "The product has been added to your wishlist";
	P05_Search search;

	@Test(priority = 1, retryAnalyzer = RetryFailures.class)
	@Description("Verify that a user can successfully add products to their wish list")

	@Severity(SeverityLevel.MINOR)
	public void addProdToWishListTest() throws InterruptedException {
		new P05_Search(driver).searchByName(prodName);
		String SuccessMessg = new P10_wishList(driver).addProdToWishList().getSuccessMessg();
		boolean flag = new HomePage(driver).goToWishlist().verifyProdAdded(prodName);
		Assert.assertTrue(SuccessMessg.contains(SuccMessg));
		Assert.assertTrue(flag);
	}

}
