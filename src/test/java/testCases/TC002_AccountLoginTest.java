package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_AccountLoginTest extends BaseClass{
	
	@Test(groups= {"Sanity","Master"})
	public void verify_Login()
	{
		
		logger.info("**************   Starting TC002_AccountRegistrationTest execution **********" );
		
		try {
		HomePage Hp = new HomePage(driver);
		Hp.clickMyaccount();
		Hp.clickLogin();
		
		LoginPage  Lp = new LoginPage(driver);
		Lp.setEmail(pro.getProperty("email"));
		Lp.setPassword(pro.getProperty("password"));
		Lp.Loginbutt();
		
		logger.info("*** Verifying My Account is exist or not ****");
		MyAccountPage Myacc = new MyAccountPage(driver);
		boolean targetmsg = Myacc.isMyaccpageExist();
		//Assert.assertTrue(targetmsg);
		Assert.assertEquals(targetmsg, true,"Login failed");
		
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("***** finished TC002_AccountRegistrationTest execution **********");
		
		
	}
	
	

}
