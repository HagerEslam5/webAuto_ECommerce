package bases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pages.HomePage;

public class BaseTest {

	public WebDriver driver;
	public HomePage HomePage;

	public void InitializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\GlobalData.properties");
		prop.load(fis);

		String BrowserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		if (BrowserName.equalsIgnoreCase("chrome")) {

			driver = new ChromeDriver();
		} else if (BrowserName.equalsIgnoreCase("fireFox")) {
			driver = new FirefoxDriver();
		} else if (BrowserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	public List<HashMap<String, String>> readJsonData(String filePath) throws IOException {

		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	public Object[][] readDataFromExcel(String filePath) throws IOException {
		DataFormatter formatter = new DataFormatter();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +filePath);
		XSSFWorkbook workBook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workBook.getSheetAt(0);
		int rowCount = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int colCount = row.getLastCellNum();

		Object data[][] = new Object[rowCount - 1][colCount];
		for (int i = 0; i < rowCount - 1; i++) {
			Row rowOfIndex = sheet.getRow(i + 1);
			for (int j = 0; j < colCount; j++) {
				Cell cell = rowOfIndex.getCell(j);
				data[i][j] = formatter.formatCellValue(cell);
			}
		}
		return data;
	}

	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//FailedTestsScreenshot//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	@BeforeClass(alwaysRun = true)
	public pages.HomePage setUp() throws IOException {
		InitializeDriver();
		driver.get("https://demo.nopcommerce.com/");
		return HomePage = new HomePage(driver);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}
