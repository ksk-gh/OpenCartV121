package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import testbase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups={"DataDriven"})
	public void verify_LoginDDT(String email, String password, String exp) throws InterruptedException {
		logger.info("*****Starting TC003_LoginDTT******");
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();

		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(password);
		lp.clickLogin();

		MyAccountPage mp = new MyAccountPage(driver);
		boolean targetPage = mp.isMyAccountPageExists();

		if (exp.equalsIgnoreCase("Valid")) {
			if (targetPage == true) {
				mp.clickLogout();
				Assert.assertTrue(true);

			} else {
				Assert.assertTrue(false);
			}
		}
		if (exp.equalsIgnoreCase("Invalid")) {
			if (targetPage == true) {
				mp.clickLogout();
				Assert.assertTrue(false);

			} else {
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e) {
			Assert.fail();
		}
		Thread.sleep(3000);
		logger.info("*****Finishing TC003_LoginDTT******");
	}
}
