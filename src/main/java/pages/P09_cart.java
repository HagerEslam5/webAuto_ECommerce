package pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import bases.BasePage;

public class P09_cart extends BasePage {
	private final WebDriver driver;

	public P09_cart(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	private final By total = By.cssSelector("span[class='value-summary'] strong");
	private final By cartItems = By.cssSelector(".product-name");
	private final By termsOfService = By.id("termsofservice");
	private final By checkOutBox = By.id("checkout");

	public int getTheTotalPrice() {
		waitForVisibiltyOfElem(this.total);
		String displayedPrice = driver.findElement(total).getText();
		return cleanThePrice(displayedPrice);
	}

	public List<String> getAllAddedProducts() {
		List<WebElement> items = driver.findElements(cartItems);
		List<String> titles = new ArrayList<>();
		for (WebElement item : items) {
			titles.add(item.getText());
		}
		return titles;
	}

	public void goToCheckOut() {
		clickOnElem(termsOfService);
		clickOnElem(checkOutBox);
	}

}
