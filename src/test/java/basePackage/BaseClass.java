package basePackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pagesCreation.LandingPage;

public class BaseClass {
	public WebDriver driver;
	public LandingPage lp;

	public WebDriver initialization() throws IOException {

		File file = new File(System.getProperty("user.dir") + "//src//main//java//resources//GlobalData.properties");
		FileInputStream fis = new FileInputStream(file);
		Properties p = new Properties();
		p.load(fis);

		String browserName = p.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	}

	public List<HashMap<String, String>> getDataFromJson(String filePath) throws IOException {
		// read json data and convert to String
		File file = new File(filePath);
		String data = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

		// convert string to hashmap
		ObjectMapper objMap = new ObjectMapper();
		// class ObjectMapper provides functionality for reading and writing JSON
		List<HashMap<String, String>> tData = objMap.readValue(data,
				new TypeReference<List<HashMap<String, String>>>() {

				});
		return tData;
	}

	public static ExtentReports getExtentReport() {
		String filePath = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(filePath);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Raga Iswariya S");
		return extent;
		}
	
	public String getScreenshot(String testcaseName,WebDriver driver) throws IOException
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest=new File(System.getProperty("user.dir")+"//reports//"+testcaseName+".png");
		FileUtils.copyFile(src, dest);
		return System.getProperty("user.dir")+"//reports//"+testcaseName+".png";
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApp() throws IOException {
		driver = initialization();
		lp = new LandingPage(driver);
		lp.goToUrl();
		return lp;
	}

	/*
	 * @AfterMethod(alwaysRun=true) public void closeApp() { driver.close(); }
	 */

}
