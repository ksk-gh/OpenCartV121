package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.AccountRegistrationPage;
import pageobjects.HomePage;
import testbase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	String fName;
	String lName;
	String password, telePhone;

	@Test(groups={"Regression","Master"})
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
