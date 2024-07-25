package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import bases.BasePage;

public class P02_Login extends BasePage {
	private final WebDriver driver;

	public P02_Login(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	private final By emailBox = By.id("Email");
	private final By passBox = By.id("Password");
	private final By loginBtn = By.cssSelector("div[class*='returning-wrapper'] div[class='buttons'] button");
	private final By logoutBtn = By.cssSelector(".ico-logout");
	private final By errorMesgg=By.cssSelector("div[class='message-error validation-summary-errors'] ul li");
	
	public P02_Login enterEmail(String email) {
		sendData(this.emailBox, email);
		return this;
	}
	
	public P02_Login enterPassword(String password) {
		sendData(this.passBox, password);
		return this;
	}


	public P02_Login clickOnLoginBtn() {
		excuteScript(this.loginBtn);
		return this;
	}

	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public void logout() {
		excuteScript(logoutBtn);
	}
	
	public String getErrorMessage() {
		waitForVisibiltyOfElem(errorMesgg);
		return driver.findElement(errorMesgg).getText();
		
	}
}
