package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import bases.BasePage;

public class P15_confirmationPage extends BasePage {

	private final WebDriver driver;

	public P15_confirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	private final By continueBtn = By.xpath("//button[@class=\"button-1 confirm-order-next-step-button\"]");
	private final By confirmationMessg = By
			.cssSelector("div[class='section order-completed'] div[class='title'] strong");

	public P15_confirmationPage clickOnContinue() {
		clickOnElem(this.continueBtn);
		return this;
	}

	public String getConfirmationMessage() {
		return driver.findElement(confirmationMessg).getText();
	}
}
