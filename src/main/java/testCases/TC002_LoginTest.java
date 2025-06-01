package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.myAccountPage;

public class TC002_LoginTest extends BaseClass{
	
	

	@Test(groups="Sanity")
	public void verify_Login() {
		
		logger.info("****Starting the TC002_LoginTest  ****");
		try {
		//Homepage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Loginpage
		LoginPage lp=new LoginPage(driver);
		lp.email(p.getProperty("mail"));
		lp.password(p.getProperty("pwd"));
		
		lp.Login();
		
		//myaccountpage
		myAccountPage map=new myAccountPage(driver);
		boolean targetpage=map.isMyAccountPageExists();
		//Assert.assertEquals(targetpage, true, "Login Failed");
		Assert.assertTrue(targetpage);
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("****Finished the TC002_LoginTest  ****");
		
		
		

}
}