package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import bases.BasePage;

public class P06_Shoes extends BasePage {

	public P06_Shoes(WebDriver driver) {
		super(driver);
	}

	private final By shoes = By.xpath("(//a[text()='Shoes '])[1]");

	private final By RandomColorFilter(int randomNum) {
		return By.id("attribute-option-" + randomNum + "");
	};

	public P06_Shoes clickOnShoes() {
		clickOnElem(this.shoes);
		return this;
	}

	public P06_Shoes filterByRandomColor() {
		int randomNum = generateRandomIndexFromColorListindecies();
		clickOnElem(this.RandomColorFilter(randomNum));
		return this;
	}

}
