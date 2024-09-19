package tests;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import bases.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.HomePage;
import pages.P08_Clothing;

public class T08_SortItems extends BaseTest {

	@Test(priority = 1)
	@Description("Verify that a user can sort products by price")

	public void sortByPriceTest() throws InterruptedException {
		new HomePage(driver).HoverOnApparel();
		P08_Clothing clothing = new P08_Clothing(driver);
		List<Integer> expectedPrices = clothing.goToClothing().getPricesAndSortThem();
		List<Integer> actualPrices = clothing.sortByPrice().getTheDisplayedPrices();
		Assert.assertTrue(expectedPrices.equals(actualPrices));
	}

}
