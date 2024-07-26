package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import bases.BaseTest;
import data.FakeData;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.P02_Login;
import pages.P04_ChangePassword;

public class T04_ChangePassword extends BaseTest {
	/*
	 * This website frequently clears its data. If the provided credentials don't
	 * work for you, please register and use your own account for the test
	 */
	private String validEmail;
	private String validPass;
	private String newPass = "Testautomation555";
	private String pgTitle = "nopCommerce demo store";
	FakeData fakeData = new FakeData();

	@BeforeClass
	public void generateData() throws IOException {
		Faker JavaFaker = new Faker();
		fakeData.setPassword(JavaFaker.internet().password());
		List<HashMap<String, String>> loginData = readJsonData(
				System.getProperty("user.dir") + "\\src\\test\\java\\data\\loginData.json");
		for (HashMap<String, String> entry : loginData) {
			validEmail = entry.get("email");
			validPass = entry.get("password");
		}
	}

	@Test(priority = 2)
	@Description("Verify that user can change the password when the new password and confirmation match")
	@Severity(SeverityLevel.MINOR)
	public void TestPasswordChange_P() {
		P02_Login loginObj = HomePage.goToLogin();
		loginObj.enterEmail(validEmail).enterPassword(validPass).clickOnLoginBtn();
		P04_ChangePassword changePas = new P04_ChangePassword(driver);
		String succesMessg = changePas.goToMyAcc().goToChangPassPage().changePass(validPass, newPass, newPass)
				.getSuccessMessg();
		changePas.closeBar().logout();
		Assert.assertEquals(succesMessg, "Password was changed");
		
		//SignIn using the new password
		HomePage.goToLogin().enterEmail(validEmail).enterPassword(validPass).clickOnLoginBtn();
		String title = loginObj.getPageTitle();
		Assert.assertEquals(title, pgTitle);

	}

	@Test(priority = 1, groups = { "Negative" })
	@Description("Verify that user cannot change the password when the new password and confirmation do not match")
	@Severity(SeverityLevel.MINOR)
	public void TestPasswordChange_N() {
		P02_Login loginObj = HomePage.goToLogin();
		loginObj.enterEmail(validEmail).enterPassword(validPass).clickOnLoginBtn();
		P04_ChangePassword changePas = new P04_ChangePassword(driver);
		String getErrorMessg = changePas.goToMyAcc().goToChangPassPage()
				.changePass(validPass, newPass, fakeData.getPassword()).getErrorMessg();
		changePas.logout();
		Assert.assertEquals(getErrorMessg, "The new password and confirmation password do not match.");

	}

}
