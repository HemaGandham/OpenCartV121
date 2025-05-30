package testCases;

import org.testng.Assert;

import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.myAccountPage;
import utilities.DataProviders;


public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="Datadriven")//getting data provider from different class
	
	public void verify_loginDDT(String mail,String pwd,String exp) {
		logger.info("****Starting the TC003_LoginDDT  ****");
		try {
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
		
		/*Data is valid-login success-test pass-logout
		 * data is valid-login failed-test failed
		 * 
		 * data is invalid-login success-test fail-logout
		 * data is invalid-login failed-test pass
		 */
		if(exp.equalsIgnoreCase("Valid")) {//data is valid
			if(targetpage==true) {//login is success
				
				map.clicklogout();//logout
				Assert.assertTrue(true);//testpass
			}
			else {
				Assert.assertTrue(false);//login failed-test failed
			}
		}
		if(exp.equalsIgnoreCase("InValid")) {//data is invalid
			if(targetpage==true) {//login is success
				
				map.clicklogout();//logout
				Assert.assertTrue(false);//test failed
			}
			else {
				Assert.assertTrue(true);//login failed-test passed
			}
		}}
		catch(Exception e){
			Assert.fail();
		}
		logger.info("****finished the TC003_LoginDDT  ****");
	}

}
