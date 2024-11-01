package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	
	//WebDriver driver;
	
	
	
	@Test(groups= {"Regression","Master"})
	public void AccountRegistartioncheck() throws InterruptedException
	{
		try {
		logger.info("**************   Starting TC001_AccountRegistrationTest execution **********" );
		HomePage Hp = new HomePage(driver);
		Hp.clickMyaccount();
		logger.info("*** Clicked on Myaccount ***");
		
		Hp.clickRegister();
		logger.info("** Clicked on Register **");
		
		
		
		RegistrationPage Regpage = new RegistrationPage(driver);
		logger.info("** providing customer details ------");
	
		Regpage.setFirstname(randomstring());
		Regpage.setlastname(randomstring());
		
		Regpage.setEmail(randomstring()+"@gmail.com");
		Regpage.setphonenum(randomNumber());
		
		String password = randomAlphanumeric();
		Regpage.setpwd(password);
		Regpage.setconfirmpwd(password);
		
		Regpage.clickplcy();
		Thread.sleep(4000);
		Regpage.buttoncontinue();
		
		logger.info("Validating confiramation message ------");
		String confmessage = Regpage.confrmmsg();
		Thread.sleep(4000);
		//Assert.assertEquals(confmessage, "Your Account Has Been Created!");
		if(confmessage.equals("Your Account Has Been Created!"))
		{
			logger.info("Test passed");
			Assert.assertTrue(true);
		}
		
		else
		{
			logger.error("Test failed");
			logger.debug("Debug log");
			Assert.assertTrue(false);
		}
		
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("***** finished TC001_AccountRegistrationTest execution **********");
	}
	
	
  
   
   
}