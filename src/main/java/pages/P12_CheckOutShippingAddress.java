package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import bases.BasePage;

public class P12_CheckOutShippingAddress extends BasePage {

	public P12_CheckOutShippingAddress(WebDriver driver) {
		super(driver);
	}

	private final By continueBtn = By.xpath("//button[@class=\"button-1 shipping-method-next-step-button\"]");

	private final By RandomShippingAddress(int randomNum) {
		return By.id("shippingoption_" + randomNum);
	};

	public P12_CheckOutShippingAddress chooseShippingAddress() {
		int random = generateRandomNumber("shippingaddress");
		clickOnElem(this.RandomShippingAddress(random));
		return this;
	}

	public P12_CheckOutShippingAddress clickOnContinue() {
		clickOnElem(this.continueBtn);
		return this;
	}

}
