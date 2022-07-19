package org.vtiger.ObjectRepository;
	import java.util.ArrayList;
	import java.util.List;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;
	import org.tyss.genericUsability.SeleniumUsability;

	public class OrganizationPage {
			WebDriver driver;
			public OrganizationPage(WebDriver driver) {
				this.driver=driver;
				PageFactory.initElements(driver, this);
			}

			@FindBy(xpath="//span[@name='Accounts_listViewCountContainerName']")
			private WebElement pageCountText;

			@FindBy(xpath="//table[@class='lvt small']/tbody/tr/td[3]/a")
			private List<WebElement> organizationNameList;

			@FindBy(xpath="//a[@alt='Next']")
			private WebElement nextButton;

			@FindBy(xpath="//div[@id='status']")
			private WebElement statusBar;

			@FindBy(xpath="//input[@class='crmbutton small delete']")
			private WebElement deleteButton;

			private String particularcheckBox="//table[@class='lvt small']/tbody/tr[%s]/td[1]/input";

			private WebElement convertFromDynamicXpathToWebElement(String elementPartialxpath, String replaceData) {
				String xpath=String.format(elementPartialxpath, replaceData);
				return driver.findElement(By.xpath(xpath));
			}
			private By convertFromDynamicXpathToBy(String elementPartialxpath, String replaceData) {
				//format is a static method present in string class
				String xpath=String.format(elementPartialxpath, replaceData);
				return By.xpath(xpath);
			}
			private By convertFromXpathToBy(String xpath) {
				return By.xpath(xpath);
			}
			/**
			 * This method is used to get the total page count in runtime and return it in integer format
			 * @return
			 */
			public int getPageCount() {
				String[] arrPageCount=pageCountText.getText().split(" ");
				int pageCount=Integer.parseInt(arrPageCount[arrPageCount.length-1]);
				return pageCount;
			}
			public void clickParticularCheckbox(String replaceData) {
				//we can  call non static method inside a non static method block directly 
				convertFromDynamicXpathToWebElement(particularcheckBox, replaceData).click();
			}
			public List<String> getProjectNameList(){
				List<String> list=new ArrayList<String>();
				//.size() method is the method of list
				for(int i=0;i<organizationNameList.size();i++) {
					list.add(organizationNameList.get(i).getText());
				}
				return list;
			}
			public void clickNext() {
				nextButton.click();
			}
			public void waitTillStatusInvisible(SeleniumUsability webDriverUtility) {
				webDriverUtility.waitTillElementInvisible(statusBar);
			}
			public void deleteOrganization(SeleniumUsability webDriverUtility) {
				deleteButton.click();
				webDriverUtility.jsPopupaccept();
			}

}
