package org.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.tyss.genericUsability.SeleniumUsability;

public class CommonPage 
{
	public CommonPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[text()='More']")
	private WebElement moreTab;
	@FindBy(name="Campaigns")
	private WebElement campaignsTab;
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement administratorIcon;
	@FindBy(xpath="//a[text()='Sign Out']")
	private WebElement signOutLink;
	
	//business library
	 /**
	  * This method is used to click on more tab
	  * @param seleniumusability
	  */
	public void clickMore(SeleniumUsability seleniumusability) 
	{
		seleniumusability.mouseHoverOnElement(moreTab);	
		moreTab.click();
	}
	
	public void clickCampaign()
	{
		campaignsTab.click();
	}
	
	/**
	 * This method is used to signout from the application
	 * @param seleniumusability
	 */
	public void clickSignOut(SeleniumUsability seleniumusability)
	{
		seleniumusability.mouseHoverOnElement(administratorIcon);
		signOutLink.click();
	}
	

}
