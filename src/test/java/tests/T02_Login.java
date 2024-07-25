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

public class T02_Login extends BaseTest {

	private FakeData FakeData = new FakeData();
	/*
	 * This website frequently clears its data. If the provided credentials don't
	 * work for you, please register and use your own account for the test
	 */
	private String validEmail;
	private String validPass;
	private String pgTitle = "nopCommerce demo store";
	private String invaildPassFailedMessg = "The credentials provided are incorrect";
	private String invaildEmailFailedMessg = "No customer account found";

	@BeforeClass
	public void generateData() throws IOException {
		Faker JavaFaker = new Faker();
		FakeData.setEmail(JavaFaker.internet().emailAddress());
		FakeData.setPassword(JavaFaker.internet().password());
		List<HashMap<String, String>> loginData = readJsonData(
				System.getProperty("user.dir") + "\\src\\test\\java\\data\\loginData.json");
		for (HashMap<String, String> entry : loginData) {
			validEmail = entry.get("email");
			validPass = entry.get("password");
		}
	}

	@Test(priority = 1)
	@Description("Verify that a user can log in with a valid email and password")
	@Severity(SeverityLevel.CRITICAL)
	public void TestLogin_P() {

		P02_Login loginObj = HomePage.goToLogin();
		loginObj.enterEmail(validEmail).enterPassword(validPass).clickOnLoginBtn();
		String title = loginObj.getPageTitle();
		Assert.assertEquals(title, pgTitle);
		loginObj.logout();
	}

	@Test(priority = 2, groups = { "Negative" })
	@Description("Verify that a user cannot log in with a valid email and an invalid password")
	@Severity(SeverityLevel.CRITICAL)
	public void TestInvalidPasswordLogin_N() {
		P02_Login loginObj = HomePage.goToLogin();
		loginObj.enterEmail(validEmail).enterPassword(FakeData.getPassword()).clickOnLoginBtn();
		String errorMessage = loginObj.getErrorMessage();
		Assert.assertEquals(errorMessage, invaildPassFailedMessg);
	}

	@Test(priority = 3, groups = { "Negative" })
	@Description("Verify that a user cannot log in with a invalid email and an valid password")
	@Severity(SeverityLevel.CRITICAL)
	public void TestInvalidEmailLogin_N() {
		P02_Login loginObj = HomePage.goToLogin();
		loginObj.enterEmail(FakeData.getEmail()).enterPassword(validPass).clickOnLoginBtn();
		String errorMessage = loginObj.getErrorMessage();
		Assert.assertEquals(errorMessage, invaildEmailFailedMessg);
	}

}
