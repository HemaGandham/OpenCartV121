package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class myAccountPage extends BasePage {

	public myAccountPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath="//h2[text()='My Account']")
	WebElement msgHeading;
	@FindBy(xpath="//div[@class='list-group']//a[text()='Logout']")
	WebElement lnkLogout;
	
	public boolean isMyAccountPageExists()
	{
		try {
			return(msgHeading.isDisplayed());
		}
		catch(Exception e) {
			return false;
		}
		
	}
	public void clicklogout() {
		lnkLogout.click();
		
	}

}
