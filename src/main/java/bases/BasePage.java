package bases;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	protected WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public void excuteScript(By locator) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(locator));
	}

	public void waitForVisibiltyOfElem(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public void sendData(By byLocator, String text) {
		driver.findElement(byLocator).sendKeys(text);
	}

	public void clickOnElem(By byLocator) {
		driver.findElement(byLocator).click();
	}

	public void scrollIntoView(By element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(element));
	}

	public void scrollDown() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 500)");
	}

	public int generateRandomIndexFromColorListindecies() {
		int[] indecies = { 14, 15, 16 };
		Random random = new Random();
		int randomIndex = random.nextInt(indecies.length);
		int randomNumber = indecies[randomIndex];
		return randomNumber;
	}

	// Helper method to determine the bound based on the method
	private int getBoundForMethod(String usedMethod) {
		switch (usedMethod) {
		case "shippingaddress":
			return 3; // Random number between 0 and 2
		case "creditcard":
			return 4; // Random number between 0 and 3
		default:
			throw new IllegalArgumentException("Invalid method: " + usedMethod);
		}
	}

	// Method to generate a random number based on the requirement
	public int generateRandomNumber(String usedMethod) {
		Random random = new Random();
		int bound = getBoundForMethod(usedMethod);
		return random.nextInt(bound);
	}

	/*public String openLinkInNewTab() throws InterruptedException {
		String clickOnLink = Keys.chord(Keys.CONTROL, Keys.ENTER);
		Thread.sleep(1000);
		return clickOnLink;
	}*/
	public String getWindowHandles(){
		Set<String> windowIDs = driver.getWindowHandles();
		Iterator<String> it = windowIDs.iterator();
		return it;
	}

	//Same method with selenium4 new feature
		public void openLinkInNewTab(String link) throws InterruptedException {
		WebDriver newTab = driver.switchTo().newWindow(WindowType.TAB);
		newTab.get(link);
		Thread.sleep(1000);
	}

	public int cleanThePrice(String priceText) {
		if (priceText.startsWith("From ")) {
			priceText = priceText.replace("From ", "");
		}

		priceText = priceText.replace("$", "").replace(".00", "");
		return Integer.parseInt(priceText);
	}

	public void selectFromDropDownByVisibleText(By dropDownLocator, String visibleText) {
		WebElement dp = driver.findElement(dropDownLocator);
		Select dropDown = new Select(dp);
		dropDown.selectByVisibleText(visibleText);
	}

	public void selectFromDropDownByindex(By dropDownLocator, int index) {
		WebElement dp = driver.findElement(dropDownLocator);
		Select dropDown = new Select(dp);
		dropDown.selectByIndex(index);
	}

	public void selectFromDropDownByValue(By byLocator, String value) {
		Select dropDown = new Select(driver.findElement(byLocator));
		dropDown.selectByValue(value);
	}
}
