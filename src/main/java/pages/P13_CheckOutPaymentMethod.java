package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import bases.BasePage;

public class P13_CheckOutPaymentMethod extends BasePage {

	public P13_CheckOutPaymentMethod(WebDriver driver) {
		super(driver);
	}

	private final By chequeMethod = By.id("paymentmethod_0");
	private final By creditMethod = By.id("paymentmethod_1");
	private final By continueBtn = By.xpath("//button[@class=\"button-1 payment-method-next-step-button\"]");
	private final By orderAddress = By.xpath("//div[@class='info']/table/tbody/tr/td/p/br[3]");

	public P13_CheckOutPaymentMethod chooseChequePaymentMethod() {
		clickOnElem(this.chequeMethod);
		return this;
	}

	public P13_CheckOutPaymentMethod chooseCreditPaymentMethod() {
		clickOnElem(this.creditMethod);
		return this;
	}

	public P13_CheckOutPaymentMethod clickOnContinue() {
		clickOnElem(this.continueBtn);
		return this;
	}

	public String getOrderAddress() {
		return driver.findElement(orderAddress).getText();
	}
}
