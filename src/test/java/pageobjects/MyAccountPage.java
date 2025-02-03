package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(how=How.XPATH,using="//h2[text()='My Account']")
	WebElement msgHeading;
	
	@FindBy(how=How.XPATH,using="//div[@class='list-group']//a[text()='Logout']") //added in step 6
	WebElement lnkLogout;
	
	public boolean isMyAccountPageExists() {
		try {
			return msgHeading.isDisplayed();
		}
		catch(Exception e) {
			return false;	
		}
		
	}
	
	public void clickLogout() {
		lnkLogout.click();
	}

}
