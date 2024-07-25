package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import bases.BasePage;

public class P10_wishList extends BasePage {
	private final WebDriver driver;

	public P10_wishList(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	private final By prodsName = By.cssSelector(".product-name");
	private final By WishListBtn = By.cssSelector(".add-to-wishlist-button");
	private final By successMessg = By.id("bar-notification");
	private final By bar = By.cssSelector(".close");

	public P10_wishList addProdToWishList() throws InterruptedException {
		scrollDown();
		Thread.sleep(1000);
		List<WebElement> buttons = driver.findElements(this.WishListBtn);
		for (WebElement button : buttons) {
			button.click();
		}
		return this;
	}

	public String getSuccessMessg() {
		waitForVisibiltyOfElem(successMessg);
		String mesgg = driver.findElement(successMessg).getText();
		clickOnElem(bar);
		return mesgg;

	}

	public boolean verifyProdAdded(String prodName) {
		List<WebElement> allProds = driver.findElements(prodsName);
		boolean flag = allProds.stream().anyMatch(s -> s.getText().contains(prodName));
		return flag;
	}
}
