package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import bases.BasePage;

public class HomePage extends BasePage {
	final private WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}

	private final By registerBtn = By.cssSelector(".ico-register");
	private final By loginBtn = By.cssSelector(".ico-login");
	private final By wishlistBtn = By.cssSelector(".ico-wishlist");
	private final By cartBtn = By.cssSelector(".ico-cart");
	private final By apparelBox = By.xpath("(//a[text()='Apparel '])[1]");

	public P01_Registration goToRegistration() {
		excuteScript(registerBtn);
		P01_Registration Registration = new P01_Registration(driver);
		return Registration;
	}

	public P02_Login goToLogin() {
		excuteScript(loginBtn);
		P02_Login login = new P02_Login(driver);
		return login;
	}

	public P09_cart goToCart() {
		excuteScript(cartBtn);
		P09_cart cart = new P09_cart(driver);
		return cart;
	}

	public P10_wishList goToWishlist() {
		excuteScript(wishlistBtn);
		P10_wishList wishList = new P10_wishList(driver);
		return wishList;
	}

	public HomePage HoverOnApparel() {
		Actions a = new Actions(driver);
		WebElement apparel = driver.findElement(this.apparelBox);
		a.moveToElement(apparel).build().perform();
		return this;
	}

}
