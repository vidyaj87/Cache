/**
 *Copyright (c) 2014 Qburst Technologies, Inc. All Rights Reserved.
 */
package configure;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class Driver {
	public WebDriver driver;
	public String browserName;
	public String username;
	public String accesskey;

	/**
	 * The function to get the url in the browser
	 */
	public void get(String url) {
		driver.get(url);
	}

	@BeforeClass
	@Parameters({ "sauce", "browser", "username", "key", "os",
			"browserVersion", "sauceLabsUrl", "loctest" })
	public void testOpenUrl(@Optional("false") String sauce,
			@Optional("firefox") String browser, @Optional("") String userName,
			@Optional("") String accessKey, @Optional("") String os,
			@Optional("") String browserVersion,
			@Optional("") String sauceLabsUrl, @Optional("") String loctest)
			throws IOException {
		try {
			DesiredCapabilities capabilities = null;

			browserName = browser;
			if (sauce.equals("true")) {
				capabilities = new DesiredCapabilities();
				username = userName;
				accesskey = accessKey;
				capabilities.setBrowserName(browser);
				capabilities.setCapability("version", browserVersion);
				capabilities.setCapability("platform", os);
				driver = new RemoteWebDriver(new URL("http://" + userName + ":"
						+ accessKey + sauceLabsUrl), capabilities);
			} else {
				if (browser.equals("chrome")) {
					System.setProperty("webdriver.chrome.driver",
							System.getProperty("user.dir")
									+ "\\chromedriver.exe");
					ChromeOptions options = new ChromeOptions();
					// ChromeOptions.AddExcludedArgument("ignore-certifcate-errors");
					DesiredCapabilities capabilities2 = DesiredCapabilities
							.chrome();
					options.addArguments("test-type");
					capabilities2.setCapability("chrome.binary",
							System.getProperty("user.dir")
									+ "\\chromedriver.exe");
					capabilities2.setCapability(ChromeOptions.CAPABILITY,
							options);
					driver = new ChromeDriver(capabilities2);
					driver.manage().timeouts()
							.implicitlyWait(500, TimeUnit.SECONDS);
					driver.manage().window().maximize();

				} else if (browser.equals("firefox")) {
					FirefoxProfile firefoxProfile = new FirefoxProfile();
					firefoxProfile.setAcceptUntrustedCertificates(true);
					//sssdriver = new FirefoxDriver(firefoxProfile);
					driver.manage().timeouts()
							.implicitlyWait(500, TimeUnit.SECONDS);
					WebElement html = driver.findElement(By.tagName("html"));
					html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
					driver.manage().window().maximize();
				} else if (browser.equals("safari")) {
					capabilities = new DesiredCapabilities();
					capabilities.setPlatform(Platform.WINDOWS);
					driver = new SafariDriver();
					driver.manage().window().maximize();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	/**
	 * The function to close the browser
	 */
	public void closeUrl() {
		driver.quit();
	}

	@AfterSuite
	/**
	 * To ensure that the driver has been closed or not
	 */
	public void tearDown() {
		boolean hasQuit = (driver.toString().contains("null")) ? true : false;
		if (hasQuit == false) {
			driver.quit();
		}
	}
}