package testcases;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageobjects.AccountRegistrationPage;
import pageobjects.HomePage;


public class TC004_AccountRegistrationTest {
	String fName;
	String lName;
	String password, telePhone;
	//private static final Logger logger = LogManager.getLogger(TC004_AccountRegistrationTest.class);
	public WebDriver driver;
	public Logger logger;
	public Properties property;
	
	public TC004_AccountRegistrationTest() {
		this.logger = LogManager.getLogger(getClass());
	}
	//@BeforeClass
	@BeforeClass(alwaysRun = true)
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {
		//Loading config.properties file
		FileReader file= new FileReader("./src/test/resources/config.properties");
		property=new Properties();
		property.load(file);
		

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
			System.out.println("Invalid Browser Name..");
			return;
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(property.getProperty("appURL1"));//reading URL from property file
		driver.manage().window().maximize();
	}
	//@AfterClass
	@AfterClass(alwaysRun = true) //@AfterTest
	public void tearDown() {
		driver.quit();
	}

	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomNumber() {
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}

	public String randomAlphaNumeric() {
		String str = RandomStringUtils.randomAlphabetic(5);
		String num = RandomStringUtils.randomNumeric(3);
		return str + "@" + num;
	}


	@Test(groups={"Single"})
	public void verifyAccountRegistration() throws InterruptedException {
		logger.info("*****Starting TC001_AccountRegistrationTest ****");
		fName = randomString();
		lName = randomString();
		password = randomAlphaNumeric();
		telePhone= randomNumber();
		
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account link");
		hp.clickRegister();
		logger.info("Clicked on Register link");
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		
		regpage.setFirstName(fName.toUpperCase());
		regpage.setLastName(lName.toUpperCase());
		regpage.setEmail(fName + "." + lName + "@gmail.com");
		regpage.setTelephone(telePhone);
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		
		Thread.sleep(10000);
		regpage.clickContinue();
		
		logger.info("Validating Expected message...");
		String confirmationMsg = regpage.getConfirmationMsg();
		if(confirmationMsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}else
		{
			logger.error("Test Failed..");
			logger.debug("Debug logs...");
			Assert.assertTrue(false);
		}
		Thread.sleep(10000);
		//Assert.assertEquals(confirmationMsg, "Your Account Has Been Created!!!");
		}
		catch(Exception e) {
			//logger.error("Test Failed...");
			//logger.debug("Debug logs...");
			Assert.fail();
		}
		
		logger.info("******Finished TC001_AccountRegistrationTest******");
	}

	
}
