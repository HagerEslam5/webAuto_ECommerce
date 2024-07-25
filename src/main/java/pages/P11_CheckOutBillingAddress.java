package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import bases.BasePage;

public class P11_CheckOutBillingAddress extends BasePage {

	public P11_CheckOutBillingAddress(WebDriver driver) {
		super(driver);
	}

	private final By firstNameBox = By.id("BillingNewAddress_FirstName");
	private final By lastNameBox = By.id("BillingNewAddress_LastName");
	private final By emailBox = By.id("BillingNewAddress_Email");
	private final By companyBox = By.id("BillingNewAddress_Company");
	private final By countryDropDown = By.id("BillingNewAddress_CountryId");
	private final By cityBox = By.id("BillingNewAddress_City");
	private final By address1Box = By.id("BillingNewAddress_Address1");
	private final By zipCodeBox = By.id("BillingNewAddress_ZipPostalCode");
	private final By phoneNumberBox = By.id("BillingNewAddress_PhoneNumber");
	private final By termsOfService = By.id("termsofservice");
	private final By checkoutBox = By.id("checkout");
	private final By continueBtn = By.cssSelector("button[class*='next-step-button']");

	public P11_CheckOutBillingAddress goToCheckOut() {
		clickOnElem(this.termsOfService);
		clickOnElem(this.checkoutBox);
		return this;
	}

	public P11_CheckOutBillingAddress insertFirstName(String firstName) {
		sendData(this.firstNameBox, firstName);
		return this;
	}

	public P11_CheckOutBillingAddress insertLastName(String lastName) {
		sendData(this.lastNameBox, lastName);
		return this;
	}

	public P11_CheckOutBillingAddress insertEmail(String email) {
		sendData(this.emailBox, email);
		return this;
	}

	public P11_CheckOutBillingAddress insertCompany(String company) {
		sendData(this.companyBox, company);
		return this;
	}

	public P11_CheckOutBillingAddress selectFromcountryDropDown(String visibleText) {
		selectFromDropDownByVisibleText(this.countryDropDown, visibleText);
		return this;
	}

	public P11_CheckOutBillingAddress insertAddress(String address) {
		sendData(this.address1Box, address);
		return this;
	}

	public P11_CheckOutBillingAddress insertZipCode(String zipCode) {
		sendData(this.zipCodeBox, zipCode);
		return this;
	}

	public P11_CheckOutBillingAddress insertCity(String city) {
		sendData(this.cityBox, city);
		return this;
	}

	public P11_CheckOutBillingAddress insertPhoneNumber(String phoneNumber) {
		sendData(this.phoneNumberBox, phoneNumber);
		return this;
	}

	public P11_CheckOutBillingAddress clickOnContinue() {
		clickOnElem(this.continueBtn);
		return this;
	}

}
