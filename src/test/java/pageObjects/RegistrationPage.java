package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public class RegistrationPage extends BasePage{
	
	public RegistrationPage(WebDriver driver)
	{
		super(driver);
	}

	

  @FindBy(xpath="//input[@id='input-firstname']") 
  WebElement txtFirstname;

 @FindBy(xpath="//input[@id='input-lastname']")
 WebElement txtLastname;

 @FindBy(xpath="//input[@id='input-email']")
 WebElement txtEmail;
 
  @FindBy(xpath="//input[@id='input-telephone']")
  WebElement txttelphone;
  
 @FindBy(xpath="//input[@id='input-password']")
 WebElement txtpwd;
 
 @FindBy(xpath="//input[@id='input-confirm']")
 WebElement txtconfirmPwd;
 
 @FindBy(xpath="//input[@name='agree']")
 WebElement checkpolicy;
 
 @FindBy(xpath="//input[@value='Continue']")
 WebElement continuebutt;
 
 
 @FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
 WebElement conformationmsg;
 
 
 
 // Action methods
 public void setFirstname(String firstname)
 {
	 txtFirstname.sendKeys(firstname);
	 
 }
 
 public void setlastname(String Lastname)
 {
	 txtLastname.sendKeys(Lastname);
 }
 
 public void setEmail(String Email)
 {
	 txtEmail.sendKeys(Email);
 }
 
 public void setphonenum(String phonenum)
 {
	 txttelphone.sendKeys(phonenum);
 }
 
 public void setpwd(String pwd)
 {
	 txtpwd.sendKeys(pwd);
 }
 
 public void setconfirmpwd(String confirmpwd)
 {
	 txtconfirmPwd.sendKeys(confirmpwd);
 }
 
 public void clickplcy()
 {
	 checkpolicy.click();
 }
 
 public void buttoncontinue()
 {
	 continuebutt.click();
 }
 
 public String confrmmsg()
 {
	 try {
 
	       return (conformationmsg.getText());
	     }
	 
	 catch(Exception e)
	 {
		 return (e.getMessage());
	 }
 }
 
	
}
