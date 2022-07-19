package org.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignInformationpage 
{
	public CampaignInformationpage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//span[@id='dtlview_Campaign Name']")
	private WebElement actualCampaignNameText;
	
	//Business Library
	
	/**
	 * This method is used to click on the create campaign button
	 * @return
	 */
	public String getActualCampaignName()
	{
		return actualCampaignNameText.getText();
	}

}
