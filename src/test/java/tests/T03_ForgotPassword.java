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
import pages.HomePage;
import pages.P03_ForgotPassword;

public class T03_ForgotPassword extends BaseTest {
	private FakeData FakeData = new FakeData();
	/*
	 * This website frequently clears its data. If the provided credentials don't
	 * work for you, please register and use your own account for the test
	 */
	private String validEmail;
	private String validRecvoryMessg = "Email with instructions has been sent to you.";
	private String invalidRecvoryMessg = "Email not found.";

	@BeforeClass
	public void generateData() throws IOException {
		Faker JavaFaker = new Faker();
		FakeData.setEmail(JavaFaker.internet().emailAddress());

		List<HashMap<String, String>> loginData = readJsonData(
				System.getProperty("user.dir") + "\\src\\test\\java\\data\\loginData.json");
		for (HashMap<String, String> entry : loginData) {
			validEmail = entry.get("email");
		}
	}

	@Test(priority = 1)
	@Description("Verify that user can recover the password using a valid email address")
	@Severity(SeverityLevel.CRITICAL)
	public void TestPasswordRecovering_P() {
		new HomePage(driver).goToLogin();
		P03_ForgotPassword ForgotPassword = new P03_ForgotPassword(driver);
		ForgotPassword.clickOnForgotPassLink().enterEmail(validEmail).clickOnRecoveryBtn();
		String messg = ForgotPassword.getMessage();
		Assert.assertEquals(messg, validRecvoryMessg);

	}

	@Test(priority = 2, groups = { "Negative" })
	@Description("Verify that user cannot recover the password using an invalid email address")
	@Severity(SeverityLevel.NORMAL)
	public void TestPasswordRecovering_N() {
		new HomePage(driver).goToLogin();
		P03_ForgotPassword ForgotPassword = new P03_ForgotPassword(driver);
		ForgotPassword.clickOnForgotPassLink().enterEmail(FakeData.getEmail()).clickOnRecoveryBtn();
		String messg = ForgotPassword.getMessage();
		Assert.assertEquals(messg, invalidRecvoryMessg);

	}

}
