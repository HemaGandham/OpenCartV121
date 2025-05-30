package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	 WebElement FistName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	 WebElement LastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement Email;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement Telephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement Password;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement PasswordConfirm;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement Agree;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement Submit;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	
	public void firstname(String fname)
	{
		FistName.sendKeys(fname);
	}
	
	public void lastname(String sname)
	{
		LastName.sendKeys(sname);
	}
	public void email(String email)
	{
		Email.sendKeys(email);
	}
	public void telephone(String tel)
	{
		Telephone.sendKeys(tel);
	}
	public void password(String pass)
	{
		Password.sendKeys(pass);
	}
	public void confirmpassword(String cfrmpass)
	{
		PasswordConfirm.sendKeys(cfrmpass);
	}
	public void agree()
	{
		Agree.click();
	}
	public void submit(){
		Submit.click();
	}
	
	public String getConfirmationmsg()
	{
		try {
			return(msgConfirmation.getText());
		}
		catch(Exception e) {
			return(e.getMessage());
		}
	}
	
}
