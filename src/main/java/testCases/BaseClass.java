package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


import org.apache.logging.log4j.Logger;

public class BaseClass {
	public static WebDriver driver; //the diver shouldn't conflict the driver in the case of object creation
	public Logger logger;// log4j2
	public Properties p;

	@SuppressWarnings("deprecation")
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({ "os", "browser" })
	void setup(String os, String br) throws IOException {

		//loading config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);

		logger = LogManager.getLogger(this.getClass());//log4j2

		if(p.getProperty("execution_env").equalsIgnoreCase("remote")){
			DesiredCapabilities cap=new DesiredCapabilities();

			//checking os
			if(os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac")){
				cap.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("No matching os");
				return;
			}

			//browser
			switch(br.toLowerCase())
			{
				case "chrome":cap.setBrowserName("chrome");break;
				case "edge":cap.setBrowserName("MicrosoftEdge");break;
				default: System.out.println("No matching browser");return;
			}

			WebDriverManager.chromedriver().setup(); // Make sure WebDriverManager is in your pom.xml
			ChromeOptions options = new ChromeOptions();
			driver =new ChromeDriver(options);


			//driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);

		}

		//local
		/*if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
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

		}*/

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL1"));//reading url from properties file
		//driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
	}

	@SuppressWarnings("deprecation")
	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;

	}

	@SuppressWarnings("deprecation")
	public String randomnumber() {
		String generatednumber = RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}

	public String randomAlphanumeric() {
		@SuppressWarnings("deprecation")
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		@SuppressWarnings("deprecation")
		String generatednumber = RandomStringUtils.randomNumeric(10);
		return (generatedString + "@" + generatednumber);
	}

	public String captureScreen(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

		System.out.println("path:-------   "+targetFilePath);
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;
	}

	@AfterClass(groups= {"Sanity","Regression","Master"})
	void tearDown() {
		driver.quit();
	}

}
