package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; // Log4J
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties pro;

	@BeforeClass(groups = { "Regression", "Master", "Sanity" })
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {
		// loading config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		pro = new Properties();
		pro.load(file);

		logger = LogManager.getLogger(this.getClass());

		if (pro.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capbilities = new DesiredCapabilities();

			// setting os
			if (os.equalsIgnoreCase("Windows")) {
				capbilities.setPlatform(Platform.WIN10);

			}

			else if (os.equalsIgnoreCase("MAC")) {
				capbilities.setPlatform(Platform.MAC);

			}

			else {
				System.out.println("Invalid os");
				return;
			}

			// Browser setup

			switch (br.toLowerCase()) {
			case "chrome":
				capbilities.setBrowserName("chrome");
				break;
			case "edge":
				capbilities.setBrowserName("edge");
				break;
			case "firefox":
				capbilities.setBrowserName("firefox");
				break;
			default:
				System.out.println("Invalid browser");
				return;

			}
			
			driver= new RemoteWebDriver(new URL("http://192.168.1.5:4444/wd/hub"),capbilities);

		}
		if (pro.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid browser");
				return;

			}
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// driver.get("https://tutorialsninja.com/demo/");
		driver.get(pro.getProperty("appurl")); // reading url from properties file
		driver.manage().window().maximize();

	}

	@AfterClass(groups = { "Regression", "Master", "Sanity" })
	public void tearDown() {
		driver.quit();
	}

	public String randomstring() {
		// @SuppressWarnings("deprecation")
		String randomstring = RandomStringUtils.randomAlphabetic(5);
		return randomstring;
	}

	public String randomNumber() {
		String randomnum = RandomStringUtils.randomNumeric(10);
		return randomnum;
	}

	public String randomAlphanumeric() {
		String randomalphanum = RandomStringUtils.randomAlphanumeric(6);
		return randomalphanum;
	}

	public String captureScreen(String tname) {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "-" + timeStamp;
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;
	}

}
