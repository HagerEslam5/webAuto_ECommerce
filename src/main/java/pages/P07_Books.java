package pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import bases.BasePage;

public class P07_Books extends BasePage {
	public P07_Books(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	private WebDriver driver;
	private final By booksBox = By.xpath("(//a[text()='Books '])[1]");
	private final By productTitle = By.cssSelector("h2[class*='product-title']");
	private final By items = By.cssSelector("div[class='picture'] a");
	private final By addToCartBtn = By.cssSelector("button[class*='add-to-cart-button']");
	private final By productPrice = By.cssSelector("div.product-price span");

	private final By bar = By.cssSelector(".close");

	public P07_Books clickOnBooksBox() {
		clickOnElem(this.booksBox);
		return this;
	}

	public List<String> getAllDisplayedProds() {
		List<WebElement> items = driver.findElements(productTitle);
		List<String> titles = new ArrayList<>();
		for (WebElement item : items) {
			titles.add(item.getText());
		}
		return titles;
	}

	/*public P07_Books openAllItemsInNewTabs() throws InterruptedException {
		List<WebElement> links = driver.findElements(this.items);
		for (int i = 0; i < links.size(); i++) {
			String clickOnLink = openLinkInNewTab();
			links.get(i).sendKeys(clickOnLink);
		}
		return this;
	}*/
	//Same method after updating openLinkInNewTab method in basePage
	public P07_Books openAllItemsInNewTabs() throws InterruptedException {
		List<WebElement> links =driver.findElements(items);
		for (int i = 0; i < links.size(); i++) {
			openLinkInNewTab(links.get(i).getAttribute("href"));
		}
		return this;
	}

	public int iterateToAddProductsAndGetTotal() {
		Set<String> windowIDs = driver.getWindowHandles();
		Iterator<String> it = windowIDs.iterator();
		it.next();
		int total = 0;
		while (it.hasNext()) {
			driver.switchTo().window(it.next());
			excuteScript(this.addToCartBtn);
			String priceText = driver.findElement(productPrice).getText();
			int price = cleanThePrice(priceText);
			total += price;
		}
		return total;

	}

	public P07_Books closeTheDisplayedBar() {
		clickOnElem(this.bar);
		return this;
	}

}
