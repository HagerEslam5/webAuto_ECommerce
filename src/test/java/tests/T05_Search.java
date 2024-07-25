package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import bases.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.P05_Search;

public class T05_Search extends BaseTest {
	String prodName = "HTC One M8 Android L 5.0 Lollipop";

	@Test(priority = 1)
	@Description("Verify that user can search for products by name")
	@Severity(SeverityLevel.MINOR)
	public void testSearchByProductName() {
		boolean flag = new P05_Search(driver).searchByName(prodName).getDisplayedProds(prodName);
		Assert.assertTrue(flag);
	}

	@Test(priority = 2)
	@Description("Verify that a user can search for products using a partial name")
	@Severity(SeverityLevel.MINOR)
	public void testSearchByAutoSuggs() throws InterruptedException {
		boolean flag = new P05_Search(driver).searchByName(prodName).searchByAutoSuggs("HTC", prodName);
		Assert.assertTrue(flag);
	}

}
