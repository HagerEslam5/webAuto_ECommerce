package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import bases.BasePage;

public class P14_CheckOutPaymentInfo extends BasePage {

	public P14_CheckOutPaymentInfo(WebDriver driver) {
		super(driver);

	}

	private final By creditDropDown = By.cssSelector(".dropdownlists");
	private final By continueBtn = By.xpath("//button[@class=\"button-1 payment-info-next-step-button\"]");
	private final By cardHolderName = By.id("CardholderName");
	private final By cardNumber = By.id("CardNumber");
	private final By expireMonthDropDown = By.id("ExpireMonth");
	private final By expireYearDropDown = By.id("ExpireYear");
	private final By cardCode = By.id("CardCode");

	public P14_CheckOutPaymentInfo clickOnContinue() {
		clickOnElem(this.continueBtn);
		return this;
	}

	public P14_CheckOutPaymentInfo selectRandomCreditCard() {
		selectFromDropDownByindex(this.creditDropDown, generateRandomNumber("creditcard"));
		return this;
	}

	public P14_CheckOutPaymentInfo insertCardHolderName(String name) {
		sendData(this.cardHolderName, name);
		return this;
	}

	public P14_CheckOutPaymentInfo insertCardNumber(String cardNumber) {
		sendData(this.cardNumber, cardNumber);
		return this;
	}

	public P14_CheckOutPaymentInfo selectExpireMonth(String expireMonth) {
		selectFromDropDownByVisibleText(this.expireMonthDropDown, expireMonth);
		return this;
	}

	public P14_CheckOutPaymentInfo selectexpireYearDropDown(String expireYear) {
		selectFromDropDownByVisibleText(this.expireYearDropDown, expireYear);
		return this;
	}

	public P14_CheckOutPaymentInfo insertCardCode(String cardCode) {
		sendData(this.cardCode, cardCode);
		return this;
	}

}
