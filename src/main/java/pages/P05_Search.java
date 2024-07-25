package pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import bases.BasePage;

public class P05_Search extends BasePage {
	private final WebDriver driver;

	public P05_Search(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	private final By searchBox = By.id("small-searchterms");
	private final By searchBtn = By.cssSelector("button[class*='search-box-button']");
	private final By prodTitles = By.cssSelector(".product-title");
	private final By dropDownOptions = By.id("ui-id-1");
	private final By addToCartBtn = By
			.xpath("//h2[@class='product-title']/parent::div //div[@class='add-info']/div[2]/button[1]");

	private List<WebElement> products;

	public P05_Search searchByName(String prodName) {
		sendData(this.searchBox, prodName);
		clickOnElem(this.searchBtn);
		return this;
	}

	public boolean getDisplayedProds(String prodName) {
		products = driver.findElements(prodTitles);
		boolean flag = products.stream().anyMatch(s -> s.getText().contains(prodName));
		driver.findElement(searchBox).clear();
		return flag;
	}

	public boolean searchByAutoSuggs(String partialName, String prodName) throws InterruptedException {
		sendData(searchBox, partialName);
		Thread.sleep(1000);
		List<WebElement> dropDownOpts = driver.findElements(dropDownOptions);
		boolean flag = dropDownOpts.stream().anyMatch(s -> s.getText().contains(prodName));
		driver.findElement(searchBox).clear();
		return flag;
	}

	public P05_Search chooseProd(String prodName) {
		List<WebElement> product = products.stream().filter(s -> s.getText().contains(prodName))
				.map(s -> s.findElement(this.addToCartBtn)).collect(Collectors.toList());
		product.get(0).click();
		return this;
	}
	
	public P05_Search clickOnAddToCart() throws InterruptedException{
			scrollDown();
			Thread.sleep(1000);
			List<WebElement> buttons = driver.findElements(this.addToCartBtn);
			for (WebElement button : buttons) {
				button.click();
			}
			return this;
		}
	}


