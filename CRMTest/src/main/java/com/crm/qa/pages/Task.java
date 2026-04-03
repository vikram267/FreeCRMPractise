package com.crm.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.crm.qa.base.TestBase;

public class Task extends TestBase {
	
	@FindBy(xpath="//i[contains(@class,'tasks icon')]")
	WebElement taskIcon;
	
	@FindBy(xpath="//span[normalize-space()='Tasks' and @class='item-text']")
	WebElement taskMenu;
	
	@FindBy(xpath="//span[contains(@class,'selectable') and contains(normalize-space(),'Tasks')]")
	WebElement taskTitle;
	
	@FindBy(xpath="//button[contains(normalize-space(),'Show Filters')]")
	WebElement showFilters;
	
	@FindBy(xpath="//a[@href='/tasks/board']")
	WebElement taskBoard;
	
	@FindBy(xpath="//button[normalize-space()=\"Show Mine\"]")
	WebElement showMine;
	
	@FindBy(xpath="//i[@class=\"close icon\"]")
	WebElement closeshowMineErr;
	
	@FindBy(xpath="//i[@class=\"refresh icon\"]")
	WebElement refreshIcon;
	
	@FindBy(xpath="//button[contains(@class,'linkedin')]/descendant::i[contains(@class,'edit icon')]")
	WebElement createIcon;
	
	@FindBy(xpath="//i[@class=\"save icon\"]")
	WebElement savebtn;
	
	@FindBy(xpath="//input[@name='title']")
	WebElement titleField;
		
	@FindBy(xpath="//*[contains(normalize-space(),'Assigned To')]//div[contains(@class,'fluid selection dropdown')]")
	WebElement assignedToField;
	
	@FindBy(xpath="//div[@role='listbox']/descendant::div[@class='visible menu transition']")
	WebElement priorityField;
	
	@FindBy(xpath="//div[@name='status' and @role='listbox']")
	WebElement statusField;
	
	@FindBy(xpath="//div[@name='status' and @role='listbox']//div[@role='option']//span[@class='text']")
	WebElement statusFieldInputs;
	
	@FindBy(xpath="//input[contains(@class,'calendarField react-datepicker')]")
	WebElement dueDate;
	@FindBy(xpath="//div[contains(@class,'current-month')]")
	WebElement monthHeader;
	@FindBy(xpath="//button[contains(@class,'navigation--previous')]")
	WebElement previousMonthHeader;
	@FindBy(xpath="//div[contains(@role,'option')]")
	List<WebElement> datacell;
	@FindBy(xpath="//li[contains(@class,'time-list-item')]")
	List<WebElement> timePick;
	@FindBy(xpath="//input[@name='completion']")
	WebElement completion;
	@FindBy(xpath="//div[@name='company']")
	WebElement comp;
	@FindBy(xpath="//div[@name='status' and @role='listbox']")
	WebElement statusDropDown;
	@FindBy(xpath="//div[@name='status']/span")
	List<WebElement> statuses;
	@FindBy(xpath="//div[@name='type' and @role='listbox']")
	WebElement typeMenu;
	@FindBy(xpath="//div[@name='type']/span")
	List<WebElement> typeDropDown;
	@FindBy(xpath="//div[@name='contact']")
	WebElement contactDropDown;
	@FindBy(xpath="//textarea[@name='description']")
	WebElement descriptionBox;
	@FindBy(xpath="//div[@name='priority']")
	List<WebElement> priorityDropDown;
	@FindBy(xpath="//input[@name='identifier']")
	WebElement identifierBox;
	@FindBy(xpath="//button[contains(@class,'linkedin')]/descendant::i[@class='save icon']")
	WebElement saveBtn;
	
	

	public Task() {
		PageFactory.initElements(driver, this);
	}
	
	public void clickTaskMenu() {
		
		actions.moveToElement(taskMenu).click().perform();
	    
	}
	
	public String getTaskTitleText() {
	    if (taskTitle.isDisplayed()) {
	        return taskTitle.getText();
	    }
	    return null;
	}
	
	public void clickShowFilterMenu() {
	    if (showFilters.isDisplayed()) {
	        showFilters.click();
	    }		
	}
	
	public void clickBoardMenu() {
		if(taskBoard.isDisplayed()) {
			taskBoard.click();
		}
	}
	public void clickShowMineMenu() {
		if(showMine.isDisplayed()) {
			showMine.click();
			wait.until(ExpectedConditions.elementToBeClickable(closeshowMineErr));
		}
	}
	
	public void clickCreateMenu(String Title,String AssignedTo,String month_year,String day,String time,String Company,String Completion,String Status,String Type,String Contact,String Description,String Pirority,String Identifier) {
		createIcon.click();
		titleField.sendKeys(Title);
		assignedToField.sendKeys(AssignedTo);
		completion.sendKeys(String.valueOf(Completion));
		comp.sendKeys(Company);
		contactDropDown.sendKeys(Contact);
		descriptionBox.sendKeys(Description);
		identifierBox.sendKeys(Identifier);
		
		
		dueDate.click();
		while(!monthHeader.getText().equalsIgnoreCase(month_year)){
			previousMonthHeader.click();
		}
		for(WebElement days:datacell) {
			if(days.getText().equalsIgnoreCase(day)) {
				days.click();
			}}	
		for(WebElement times:timePick) {
			if(times.getText().equalsIgnoreCase(time)) {
				times.click();
			}}	
		
		statusDropDown.click();
		for(WebElement sts:statuses) {
			if(sts.getText().equalsIgnoreCase(Status)) {
				sts.click();
			}	}
		
		typeMenu.click();
		for(WebElement types:typeDropDown) {
			if(types.getText().equalsIgnoreCase(Type)) {
				types.click();
			}}
		for(WebElement prior:priorityDropDown) {
			if(prior.getText().equalsIgnoreCase(Pirority)) {
				prior.click();
			}	}
			
		saveBtn.click();
		System.out.println("Task created successfully");
		
	


	
	
	
	
	
}}
