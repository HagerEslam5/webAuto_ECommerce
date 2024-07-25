package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import bases.BaseTest;
import data.FakeData;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.P01_Registration;

public class T01_Regirstration extends BaseTest {
	private FakeData FakeData = new FakeData();
	private String succMessg = "Your registration completed";
	private String MissingFirstNameMesgg = "First name is required.";
	private String MissingEmailMesgg = "Email is required.";
	private String mismatchingPasswordsMesgg = "The password and confirmation password do not match.";

	@BeforeClass
	public void generateData() {
		Faker JavaFaker = new Faker();
		FakeData.setFirstName(JavaFaker.name().firstName());
		FakeData.setLastName(JavaFaker.name().lastName());
		FakeData.setDay(String.valueOf(JavaFaker.number().numberBetween(1, 30)));
		FakeData.setMonth(String.valueOf(JavaFaker.number().numberBetween(1, 12)));
		FakeData.setYear(String.valueOf(JavaFaker.number().numberBetween(1990, 2000)));
		FakeData.setCompany(JavaFaker.company().name());
		FakeData.setEmail(JavaFaker.internet().emailAddress());
		FakeData.setPassword(JavaFaker.internet().password());
	}

	@Test(priority = 1, dataProvider = "getData")
	@Description("Verify that a user can successfully register with valid data")
	@Severity(SeverityLevel.CRITICAL)
	public void TestRegistration_P(String firstName, String lastName, String day, String month, String year,
			String email, String company, String password) {
		String confirmationMessg = HomePage.goToRegistration().selectFemaleGender().enterFirstName(firstName)
				.enterLastName(lastName).selectDayOfBirth(day).selectMonthOfBirth(month).selectYearOfBirth(year)
				.enterEmail(email).scrollToCompanyDetails().enterCompanyName(company).enterPassword(password, password)
				.clickOnRegisterBtn().getConfMess();
		new P01_Registration(driver).logout();

		Assert.assertTrue(confirmationMessg.contains(succMessg));
	}

	@Test(priority = 2, groups = { "Negative" })
	@Description("Verify that a user cannot register if any mandatory fields are missing")
	@Severity(SeverityLevel.CRITICAL)
	public void testMissingFirstName_N() {
		String ActualMessg = HomePage.goToRegistration().selectFemaleGender().enterLastName(FakeData.getLastName())
				.selectDayOfBirth(FakeData.getDay()).selectMonthOfBirth(FakeData.getMonth())
				.selectYearOfBirth(FakeData.getYear()).enterEmail(FakeData.getEmail()).scrollToCompanyDetails()
				.enterCompanyName(FakeData.getCompany()).enterPassword(FakeData.getPassword(), FakeData.getPassword())
				.clickOnRegisterBtn().getfirstNameErrorMesgg();

		Assert.assertEquals(ActualMessg, MissingFirstNameMesgg);
	}

	@Test(priority = 3, groups = { "Negative" })
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verify that a user cannot register if any mandatory fields are missing")

	public void testMissingEmail_N() {
		String ActualMessg = HomePage.goToRegistration().selectFemaleGender().enterFirstName(FakeData.getFirstName())
				.enterFirstName(FakeData.getFirstName()).enterLastName(FakeData.getLastName())
				.selectDayOfBirth(FakeData.getDay()).selectMonthOfBirth(FakeData.getMonth())
				.selectYearOfBirth(FakeData.getYear()).scrollToCompanyDetails().enterCompanyName(FakeData.getCompany())
				.enterPassword(FakeData.getPassword(), FakeData.getPassword()).clickOnRegisterBtn()
				.getEmailErrorMessg();

		Assert.assertEquals(ActualMessg, MissingEmailMesgg);
	}

	@Test(priority = 4, groups = { "Negative" })
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verify that a user cannot register if the password and confirmation do not match")

	public void testMisMatchingPasswords_N() {
		String ActualMessg = HomePage.goToRegistration().selectFemaleGender().enterFirstName(FakeData.getFirstName())
				.enterLastName(FakeData.getLastName()).selectDayOfBirth(FakeData.getDay())
				.selectMonthOfBirth(FakeData.getMonth()).selectYearOfBirth(FakeData.getYear())
				.enterEmail(FakeData.getEmail()).scrollToCompanyDetails().enterCompanyName(FakeData.getCompany())
				.enterPassword(FakeData.getPassword(), "FKDJG88").clickOnRegisterBtn().getConfirmationPassErrorMesgg();

		Assert.assertEquals(ActualMessg, mismatchingPasswordsMesgg);
	}

	@DataProvider(name = "getData")
	public Object[][] getDataFromExcel() throws IOException {
		return readDataFromExcel("\\src\\test\\java\\data\\reg_Data.xlsx");
	}

}
