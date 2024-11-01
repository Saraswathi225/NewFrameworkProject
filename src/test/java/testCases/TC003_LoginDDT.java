package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups="DataDriven") // DataProvider class present in other class
	public void verify_LoginDDT(String email,String Pwd, String exp)
	{
		
		
        logger.info("**************   Starting TC003_AccountRegistrationTest execution **********" );
     try {
		
		HomePage Hp = new HomePage(driver);
		Hp.clickMyaccount();
		Hp.clickLogin();
		
		LoginPage  Lp = new LoginPage(driver);
		Lp.setEmail(email);
		Lp.setPassword(Pwd);
		Lp.Loginbutt();
		
		logger.info("*** Verifying My Account is exist or not ****");
		MyAccountPage Myacc = new MyAccountPage(driver);
		boolean targetmsg = Myacc.isMyaccpageExist();
		
		
		if(exp.equalsIgnoreCase("valid"))
		{
			if(targetmsg==true)
			{
				Myacc.clickLogout();
				Assert.assertTrue(true);
			}
			
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		
		if(exp.equalsIgnoreCase("invalid"))
		{
			if(targetmsg==true)
			{
				Myacc.clickLogout();
				Assert.assertTrue(false);
			}
			
			else
			{
				Assert.assertTrue(true);
			}
		}
		
     }catch(Exception e)
        {
    	   Assert.fail();
        }
		
	logger.info("***** finished TC003_AccountRegistrationTest execution **********");
		
	}
	


}
