package org.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignPage 
{
	public CampaignPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//img[@alt='Create Campaign...']")
	private WebElement createCampaignBtn;
	
	//Business library
	
	/**
	 * This method is used to click on the create Campaign button
	 */
	public void clickOnCreateCampaignBtn()
	{
		createCampaignBtn.click();
	}

}
