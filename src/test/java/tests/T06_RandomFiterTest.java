package tests;

import org.testng.annotations.Test;

import bases.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.P06_Shoes;
import pages.HomePage;

public class T06_RandomFiterTest extends BaseTest {

	@Test(priority = 1)
	@Description("Verify that a user can filter products by any color")

	public void testRandomColorFilter() throws InterruptedException {
		new HomePage(driver).HoverOnApparel();
		new P06_Shoes(driver).clickOnShoes().filterByRandomColor();
	}

}
