package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.AccountRegistrationPage;
import PageObjects.HomePage;

public class TC001_AccountRegTest extends BaseClass {
	
	
	@Test(groups={"Regression","Master"})

	public void home() {
		logger.info("****Starting the TC001_AccountRegistrationPage  ****");
		try
		{
		HomePage Hp=new HomePage(driver);
		logger.info("Clicked on myaccountlink");
		Hp.clickMyAccount();
		logger.info("Clicked on myaccountlink");
		Hp.clickRegister();
		
		AccountRegistrationPage Ar=new AccountRegistrationPage(driver);
		
		logger.info("Validating the customer details....");
		Ar.firstname(randomString().toUpperCase());
		Ar.lastname(randomString().toUpperCase());
		Ar.email(randomString()+"@gmail.com");
		
		
		Ar.telephone(randomnumber());
		
		String password=randomnumber();
		Ar.password(password);
		Ar.confirmpassword(password);
		Ar.agree();
		Ar.submit();
		logger.info("Validating the expected meassage..");
		String confim=Ar.getConfirmationmsg();
		if(confim.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test Failed");
			logger.debug("Debug logs");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(confim, "Your Account Has Been Created!");
		}
		catch(Exception e){
			
			Assert.fail();
		}
		logger.info("****finished the TC001_AccountRegistrationPage  ****");
		
	}
	
	
}
