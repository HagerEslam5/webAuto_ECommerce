package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import bases.BasePage;

public class P04_ChangePassword extends BasePage {
	private final WebDriver driver;

	public P04_ChangePassword(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}

	private final By myAcc = By.cssSelector(".ico-account");
	private final By changePassBtn = By.cssSelector("li[class*='change-pass'] a");
	private final By oldPassBox = By.cssSelector("input[id='OldPassword']");
	private final By newPassBox = By.cssSelector("input[id='NewPassword']");
	private final By confPassBox = By.cssSelector("input[id='ConfirmNewPassword']");
	private final By submitBtn = By.cssSelector("button[class*='change-password']");
	private final By successMessg = By.cssSelector("div[class*='bar-notification success'] p");
	private final By successBar = By.cssSelector("span[title='Close']");
	private final By logoutBtn = By.cssSelector(".ico-logout");
	private final By errorMesgg = By.id("ConfirmNewPassword-error");

	public P04_ChangePassword goToMyAcc() {
		waitForVisibiltyOfElem(this.myAcc);
		clickOnElem(this.myAcc);
		return this;
	}

	public P04_ChangePassword goToChangPassPage() {
		clickOnElem(this.changePassBtn);
		return this;
	}

	public P04_ChangePassword changePass(String oldPass, String newPass, String confPass) {
		sendData(this.oldPassBox, oldPass);
		sendData(this.newPassBox, newPass);
		sendData(this.confPassBox, confPass);
		clickOnElem(this.submitBtn);
		return this;
	}

	public String getSuccessMessg() {
		waitForVisibiltyOfElem(successMessg);
		return driver.findElement(successMessg).getText();
	}

	public String getErrorMessg() {
		waitForVisibiltyOfElem(errorMesgg);
		return driver.findElement(errorMesgg).getText();
	}

	public P04_ChangePassword closeBar() {
		waitForVisibiltyOfElem(this.myAcc);
		excuteScript(this.successBar);
		return this;
	}

	public P04_ChangePassword logout() {
		waitForVisibiltyOfElem(logoutBtn);
		excuteScript(this.logoutBtn);
		return this;

	}

}
