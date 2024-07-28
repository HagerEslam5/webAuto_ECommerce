package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import bases.BaseTest;
import bases.RetryFailures;
import data.FakeData;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.P05_Search;
import pages.HomePage;
import pages.P01_Registration;
import pages.P11_CheckOutBillingAddress;
import pages.P12_CheckOutShippingAddress;
import pages.P13_CheckOutPaymentMethod;
import pages.P14_CheckOutPaymentInfo;
import pages.P15_confirmationPage;

public class T10CheckOut_e2e extends BaseTest {
	private FakeData FakeData = new FakeData();
	private Faker JavaFaker = new Faker();
	private String prodName = "HTC One M8 Android L 5.0 Lollipop";
	private String ExpectedMessg = "Your order has been successfully processed!";

	@BeforeClass
	public void generateData() {
		FakeData.setFirstName(JavaFaker.name().firstName());
		FakeData.setLastName(JavaFaker.name().lastName());
		FakeData.setFullName(JavaFaker.name().fullName());
		FakeData.setCompany(JavaFaker.company().name());
		FakeData.setEmail(JavaFaker.internet().emailAddress());
		FakeData.setPassword(JavaFaker.internet().password());
		FakeData.setCity(JavaFaker.address().city());
		FakeData.setAddress1(JavaFaker.address().fullAddress());
		FakeData.setCountry(JavaFaker.address().country());
		FakeData.setZipCode(JavaFaker.address().zipCode());
		FakeData.setPhoneNumber(JavaFaker.phoneNumber().phoneNumber());
		FakeData.setMonth(String.format("%02d", JavaFaker.number().numberBetween(1, 12)));
		FakeData.setYear(String.valueOf(JavaFaker.number().numberBetween(2025, 2038)));
		FakeData.setCardCode(JavaFaker.number().digits(3));
		FakeData.setCardNumber(JavaFaker.finance().creditCard().replaceAll("[^0-9]", ""));
	}

	@BeforeMethod
	public void checkout() throws InterruptedException {
		P01_Registration Registration = HomePage.goToRegistration();
		Registration.enterFirstName(FakeData.getFirstName()).enterLastName(FakeData.getLastName())
				.enterEmail(FakeData.getEmail()).scrollToCompanyDetails()
				.enterPassword(FakeData.getPassword(), FakeData.getPassword()).clickOnRegisterBtn();

		new P05_Search(driver).searchByName(prodName).clickOnAddToCart();
		Thread.sleep(1000);
		new HomePage(driver).goToCart();
		new P11_CheckOutBillingAddress(driver).goToCheckOut().insertFirstName(FakeData.getFirstName())
				.insertLastName(FakeData.getLastName()).insertCompany(FakeData.getCompany())
				.selectFromcountryDropDown(FakeData.getCountry()).insertCity(FakeData.getCity())
				.insertAddress(FakeData.getAddress1()).insertZipCode(FakeData.getZipCode())
				.insertPhoneNumber(FakeData.getPhoneNumber()).clickOnContinue();
		new P12_CheckOutShippingAddress(driver).chooseShippingAddress().clickOnContinue();
	}

	@AfterMethod
	public void logOut() {
		new P01_Registration(driver).logout();
		FakeData.setEmail(JavaFaker.internet().emailAddress());
	}

	@Test(priority = 1, retryAnalyzer = RetryFailures.class)
	@Description("Verify that a user can complete the checkout process using the cheque payment method")
	public void chequeCheckOutTest() throws InterruptedException {
		String orderAddress = new P13_CheckOutPaymentMethod(driver).chooseChequePaymentMethod().clickOnContinue()
				.getOrderAddress();
		new P14_CheckOutPaymentInfo(driver).clickOnContinue();
		Assert.assertTrue(FakeData.getAddress1().contains(orderAddress));
		String actualMessg = new P15_confirmationPage(driver).clickOnContinue().getConfirmationMessage();
		Assert.assertEquals(actualMessg, ExpectedMessg);
	}

	@Test(priority = 2, retryAnalyzer = RetryFailures.class)
	@Description("Verify that a user can complete the checkout process using the credit payment method")
	public void cerditCheckOutTest() throws InterruptedException {
		new P13_CheckOutPaymentMethod(driver).chooseCreditPaymentMethod().clickOnContinue();
		new P14_CheckOutPaymentInfo(driver).selectRandomCreditCard().insertCardHolderName(FakeData.getFullName())
				.insertCardNumber(FakeData.getCardNumber()).selectExpireMonth(FakeData.getMonth())
				.selectexpireYearDropDown(FakeData.getYear()).insertCardCode(FakeData.getCardCode()).clickOnContinue();
		String actualMessg = new P15_confirmationPage(driver).clickOnContinue().getConfirmationMessage();
		Assert.assertEquals(actualMessg, ExpectedMessg);
	}
}
