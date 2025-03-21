package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;


import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class)
	public void verify_loginDDT(String email,String password,String exp) 
	{
		try 
		{
			
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		logger.info("****Login Page ***********");
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(password);
		lp.clickLogin(); //Login button
		MyAccountPage macc=new MyAccountPage(driver);
		boolean target=macc.isMyAccountPageExists();
		

/*Data is valid  - login success - test pass  - logout
Data is valid -- login failed - test fail

Data is invalid - login success - test fail  - logout
Data is invalid -- login failed - test pass
*/
		
		if(exp.equalsIgnoreCase("Valid")) 
		{
			if(target==true) 
			{
				Assert.assertTrue(true);
				macc.clickLogout();
			}
		}
		else
		{
			Assert.assertTrue(false);
		}
		if(exp.equalsIgnoreCase("Ivalid")) 
		{
			if(target==true) 
			{
				
				Assert.assertTrue(false);
			}
			
		}
		else 
		{
			Assert.assertTrue(true);
		}
		
	}catch(Exception e) 
		{
			Assert.fail("An exception occurred: " + e.getMessage());
	    }
		logger.info("**** Finished TC_003_LoginDDT *****");

}}
