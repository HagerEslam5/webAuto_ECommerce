package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import bases.BasePage;

public class P08_Clothing extends BasePage {
	private final WebDriver driver;

	public P08_Clothing(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	private final By clothing = By.xpath("(//a[text()='Clothing '])[1]");
	private final By sortDropDown = By.id("products-orderby");
	private final By itemPrice = By.cssSelector("span[class*='actual-price']");

	public P08_Clothing goToClothing() {
		clickOnElem(this.clothing);
		scrollDown();
		return this;
	}

	public List<Integer> getPricesAndSortThem() {
		List<WebElement> prices = driver.findElements(itemPrice);
		List<Integer> sortedPrices = new ArrayList<>();
		for (WebElement price : prices) {
			String originalPrice = price.getText();
			sortedPrices.add(cleanThePrice(originalPrice));
		}
		return sortedPrices;
	}

	public P08_Clothing sortByPrice() {
		selectFromDropDownByVisibleText(this.sortDropDown, "Price: Low to High");
		return this;
	}

	public List<Integer> getTheDisplayedPrices() {
		List<WebElement> prices = driver.findElements(itemPrice);
		List<Integer> dispalyedPrices = new ArrayList<>();
		for (WebElement price : prices) {
			String originalPrice = price.getText();
			dispalyedPrices.add(cleanThePrice(originalPrice));
		}
		return dispalyedPrices;
	}
}
