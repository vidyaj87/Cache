package main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utility.ExcelRead;

/**
 * <p>
 * To ensure that customer can login with a valid username and password
 * </p>
 */

public class Login extends configure.Driver {

	ExcelRead excel = new ExcelRead();

	// use the Firefox browser and go to the FFrees site

	@BeforeClass
	public void setUp() {
	//	get(prop.getProperty("Baseurl"));
		 System.setProperty("webdriver.gecko.driver","/home/vidya/Documents/AutomationSelenium/library/geckodriver");

		 WebDriver driver = new FirefoxDriver();
		 
         driver.get("http://www.facebook.com");
	}

	@Test(dataProvider = "data")
	public void login(String userName, String passWord) {
		try {
			System.out.println("username :" + userName);
			System.out.println("passWord :" + passWord);
			
			 // System.setProperty("webdriver.gecko.driver","/home/vidya/Documents/AutomationSelenium/library/geckodriver");
	             
              // if above property is not working or not opening the application in browser then try below property
 
             //System.setProperty("webdriver.firefox.marionette","G:\\Selenium\\Firefox driver\\geckodriver.exe");
 
//            WebDriver driver = new FirefoxDriver();
// 
//            driver.get("http://www.facebook.com");
// 
//            System.out.println("Application title is ============="+driver.getTitle());
 
//            driver.quit();
	} catch (Exception e) {
			System.out.println("error in getTableArray()");
			e.printStackTrace();
		}
	}

	@DataProvider(name = "data")
	public Object[][] mixed() throws Exception {
		Object[][] retObjArr = excel.getTableArray("TestData//Data.xls",
				"TestData", "Login");
		return (retObjArr);
	}

}