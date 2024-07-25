package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import bases.BasePage;

public class P01_Registration extends BasePage {

	private final WebDriver driver;

	public P01_Registration(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	private final By femaleGender = By.id("gender-female");
	private final By firstNameBox = By.id("FirstName");
	private final By lastNameBox = By.id("LastName");
	private final By dayDropDown = By.name("DateOfBirthDay");
	private final By monthDropDown = By.name("DateOfBirthMonth");
	private final By yearDropDown = By.name("DateOfBirthYear");
	private final By emailBox = By.id("Email");
	private final By companyBox = By.id("Company");
	private final By passBox = By.id("Password");
	private final By ConfPassBox = By.id("ConfirmPassword");
	private final By registerBtn = By.id("register-button");
	private final By confMessg = By.cssSelector(".page-body .result");
	private final By logOutBtn = By.cssSelector(".ico-logout");
	private final By firstNameError=By.id("FirstName-error");
	private final By emailError=By.id("Email-error");
	private final By cofirmationPasswordError=By.id("ConfirmPassword-error");

	public P01_Registration selectFemaleGender() {
		clickOnElem(this.femaleGender);
		return this;
	}

	public P01_Registration enterFirstName(String firstName) {
		sendData(this.firstNameBox, firstName);
		return this;
	}

	public P01_Registration enterLastName(String lastName) {
		sendData(this.lastNameBox, lastName);
		return this;
	}

	public P01_Registration selectDayOfBirth(String day) {
		selectFromDropDownByValue(this.dayDropDown, day);
		return this;
	}

	public P01_Registration selectMonthOfBirth(String month) {
		selectFromDropDownByValue(this.monthDropDown, month);
		return this;
	}

	public P01_Registration selectYearOfBirth(String year) {
		selectFromDropDownByValue(this.yearDropDown, year);
		return this;
	}

	public P01_Registration enterEmail(String email) {
		sendData(this.emailBox, email);
		return this;
	}

	public P01_Registration scrollToCompanyDetails() {
		scrollIntoView(this.companyBox);
		return this;
	}

	public P01_Registration enterCompanyName(String companyName) {
		sendData(this.companyBox, companyName);
		return this;
	}

	public P01_Registration enterPassword(String password,String confPassword) {
		sendData(this.passBox, password);
		sendData(this.ConfPassBox, confPassword);
		return this;
	}

	public P01_Registration clickOnRegisterBtn() {
		clickOnElem(this.registerBtn);
		return this;
	}

	public String getConfMess() {
		waitForVisibiltyOfElem(confMessg);
		return driver.findElement(confMessg).getText();
}
	public String getfirstNameErrorMesgg() {
		waitForVisibiltyOfElem(firstNameError);
		return driver.findElement(firstNameError).getText();
}
	public String getEmailErrorMessg() {
		waitForVisibiltyOfElem(emailError);
		return driver.findElement(emailError).getText();
}
	public String getConfirmationPassErrorMesgg() {
		waitForVisibiltyOfElem(cofirmationPasswordError);
		return driver.findElement(cofirmationPasswordError).getText();
}

	public void logout() {
		clickOnElem(logOutBtn);
	}

}
