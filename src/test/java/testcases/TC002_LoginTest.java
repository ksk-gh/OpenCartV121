package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import testbase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups={"Sanity","Master"})
	//@Test(groups={"Master"})
	public void verify_login() {
		logger.info("*********Start TC002_LoginTest*********");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			LoginPage lp = new LoginPage(driver);
			lp.setEmail(property.getProperty("email"));
			lp.setPassword(property.getProperty("password"));
			lp.clickLogin();
			Thread.sleep(10000);
			
			MyAccountPage myaccount = new MyAccountPage(driver);
			boolean targetPage = myaccount.isMyAccountPageExists();

			Assert.assertEquals(targetPage, true, "Login Failed");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.fail();

		}
		logger.info("****Finished TC002_LoginTest****");
	}

}
