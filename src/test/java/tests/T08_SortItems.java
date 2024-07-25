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
	@Severity(SeverityLevel.MINOR)

	public void sortByPriceTest() throws InterruptedException {
		new HomePage(driver).HoverOnApparel();
		P08_Clothing clothing = new P08_Clothing(driver);
		List<Integer> expectedPrices = clothing.goToClothing().getPricesAndSortThem();
		List<Integer> actualPrices = clothing.sortByPrice().getTheDisplayedPrices();
		
		for(int pricer:expectedPrices) {
			System.out.println(pricer);
		}
		for(int pricer:actualPrices) {
			System.out.println(pricer);
		}
		Assert.assertTrue(expectedPrices.equals(actualPrices));
	}

}
