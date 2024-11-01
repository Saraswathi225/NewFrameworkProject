package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	
	@FindBy(xpath="//input[@id='input-email']") 
	WebElement txtEmailAddress;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement buttlogin;
	
	
	public void setEmail(String Email)
	{
		txtEmailAddress.sendKeys(Email);
	}
	

	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void Loginbutt()
	{
		buttlogin.click();
	}
}
