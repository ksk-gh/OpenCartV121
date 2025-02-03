package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AccountRegistrationPage extends BasePage{

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	
	}

	@FindBy(how=How.XPATH, using="//input[@id='input-firstname']")
	WebElement txtFirstName1;
	
	@FindBy(how=How.XPATH, using="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(how=How.XPATH, using="//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(how=How.XPATH, using="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(how=How.XPATH, using="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(how=How.XPATH, using="//input[@id='input-password']")
	WebElement txtPassword;

	@FindBy(how=How.XPATH, using="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(how=How.XPATH, using="//input[@name='agree']")
	WebElement chkdPolicy;
	
	@FindBy(how=How.XPATH, using="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(how=How.XPATH, using="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	
	public void setFirstName(String fName) {
		txtFirstName.sendKeys(fName);
	}
	
	public void setLastName(String lName) {
		txtLastName.sendKeys(lName);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String telephone) {
		txtTelephone.sendKeys(telephone);
	}
	
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void setConfirmPassword(String password) {
		txtConfirmPassword.sendKeys(password);
	}
	
	public void setPrivacyPolicy() {
		chkdPolicy.click();
	}
	
	public void clickContinue() {
		btnContinue.click();
		//btnContinue.submit();
	}
	
	public String getConfirmationMsg() {
		try {
			return (msgConfirmation.getText());
		}catch(Exception e) {
			return (e.getMessage());
			
		}
	}
}
