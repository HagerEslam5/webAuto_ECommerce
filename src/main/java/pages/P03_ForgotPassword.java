package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import bases.BasePage;

public class P03_ForgotPassword extends BasePage {
	private final WebDriver driver;

	public P03_ForgotPassword(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	private final By forgotPass = By.cssSelector(".forgot-password");
	private final By emailBox = By.cssSelector("#Email");
	private final By recoveryBtn = By.cssSelector(".password-recovery-button");
	private final By barMessg = By.xpath("//p[@class='content']");

	public P03_ForgotPassword clickOnForgotPassLink() {
		this.clickOnElem(forgotPass);
		return this;
	}

	public P03_ForgotPassword enterEmail(String email) {
		waitForVisibiltyOfElem(emailBox);
		sendData(this.emailBox, email);
		return this;
	}

	public P03_ForgotPassword clickOnRecoveryBtn() {
		this.clickOnElem(recoveryBtn);
		return this;
	}

	public String getMessage() {
		return driver.findElement(barMessg).getText();
	}
	
}
