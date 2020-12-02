package TestCode;



import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import com.splashbi.pageobject.BasePage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestMyCode {
	public static WebDriver driver;
	
	public static WebDriver initDriver() {
		    WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        
	        
	        return driver;
		}
	public static void login(WebDriver driver,String user, String password) {
		driver.findElement(By.id("userName")).sendKeys(user);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
	}
	public static void DragAndDropFromList(WebDriver driver, List<WebElement>l1, String name, WebElement dest) {
		for(int i=0;i<l1.size();i++) {
			System.out.println(l1.get(i).getText()+"  "+ name);
			String draggedElement = l1.get(i).getText();
		if(draggedElement.equals(name)) {	
			System.out.println(l1.get(i).getText());
			new Actions(driver).dragAndDrop(l1.get(i), dest).build().perform();
			break;
		}				
		}
	}
	public static void DragAndDrop(WebDriver driver, WebElement src, WebElement dest ) {
		 Actions act=new Actions(driver);					
        //act.clickAndHold(src).moveToElement(dest).release().build().perform();
		act.dragAndDrop(src, dest).build().perform();
	}
	
	public static void createFilterCriteria() {
		
	}
	public static void createFilterForDomainTable(WebDriver driver,String tablename) throws InterruptedException {
		driver.findElement(By.xpath("//div[contains(text(),'"+tablename+"')]")).click();
		driver.findElement(By.xpath("//div[@title='Tests517']//ul//li[@title='Filters']")).click();
		driver.findElement(By.xpath("//div[@title='Tests517']/i[@title='Create Filter Criteria']")).click();
		//column nameList
		//List<WebElement> e2 = driver.findElements(By.xpath("//div[@data-role='splitter']/div[@id='left-pane']"));
		//WebElement e1=driver.findElement(By.xpath("//div[@title='Movie Name']"));
		//WebElement movie = driver.findElement(By.xpath("//div[@data-role='splitter']/div[@id='left-pane']//span[contains(text(),'Movie Name')]"));
		WebElement move = driver.findElement(By.xpath("//div[@id='right-pane']/div[@data-role='splitter']//div[@id='tbody']"));
		//DragAndDropFromList(driver,e2,"Movie Name",move);
		WebElement moviename= driver.findElement(By.xpath("//div[@data-role='splitter']/div[@id='left-pane']//div[@title='Movie Name']"));
		DragAndDrop(driver,moviename,move);
		Thread.sleep(3000);
		WebElement filter1 = driver.findElement(By.xpath("//span[@class='td3' and contains(text(),'Movie Name')]"));
		filter1.click();
		WebElement freetext = driver.findElement(By.xpath("//input[starts-with(@class,'filterRadioButText freeText')]//following::span[1]"));
		freetext.click();
		WebElement textarea = driver.findElement(By.xpath("//div[@class='filter_type_normal clear-both sbi2-free_text']//textarea"));
	    textarea.sendKeys("MOVIE_NAME = 'The Dark Tower'");
	    WebElement filter_save=driver.findElement(By.xpath("(//input[starts-with(@class,'sbig-form-button save_table_filters_on_event') and @value='OK'])[2]"));
	    filter_save.click();
	   // repeat 59 to 76 for another filter
	    //click report link
	    
	}
	
	public static void createdomain(WebDriver driver) {
		driver.findElement(By.xpath("//div[@id='createDomainId']")).click();
		driver.findElement(By.xpath("//input[@validationmessage='Enter The Domain Name']")).sendKeys("Tests517");
		driver.findElement(By.xpath("//span[@aria-owns='businessareas_drop_listbox']")).click();
		driver.findElement(By.xpath("//li[contains(text(),'autoBusinessApplication')]")).click();
		driver.findElement(By.xpath("//div[@class='sbi2-popup-row']//span")).click();
		driver.findElement(By.xpath("//span[@title='Create Folder']")).click();
		driver.findElement(By.xpath("//input[@id='inputPassword3']")).sendKeys("Tests517");
		driver.findElement(By.xpath("//span[@aria-owns='domaingrpconnections_drop_listbox']")).click();
		driver.findElement(By.xpath("//li[contains(text(),'automationConnection')]")).click();
		driver.findElement(By.xpath("//input[@class='btn sbig-form-button save_domain sbi2-prm-btn ']")).click();
		
		
		
		
		
	}
	public static void main(String args[]) throws InterruptedException, IOException {
		Runtime.getRuntime().exec("TASKKILL /IM chromedriver.exe /F");
		BasePage base = new BasePage(driver);
		driver = initDriver();
		driver.get("http://202.153.37.200:7001/SplashBI/en_US/Login.html");
		login(driver,"surjit","welcome@123");
		//base.waitForPageLoad();
		Thread.sleep(30000);
		driver.findElement(By.xpath("//li[@id='domainLink']/a")).click();
		//base.waitForPageLoad();
		createdomain(driver);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@tabindex='-1']/input")).sendKeys("MS_PRODUCTS");
		Thread.sleep(1000);
		List<WebElement>srclist = driver.findElements(By.xpath("//*[contains(@class,'sbi-do-js-select-entity-to-drag')]/following-sibling::span"));
       //WebElement from = driver.findElement(By.xpath("//div[@class='list1 selectedObjects']//span[@title='MS_PRODUCTS']"));
		WebElement dest=driver.findElement(By.xpath("//*[contains(@class,'sbi2-edit-domain-table-header-space')]"));
		WebElement to = driver.findElement(By.xpath("//div[@data-role='droptarget']"));
	//	String value = from.getAttribute("title");
		//System.out.println("Title is:"+value);
		DragAndDrop(driver,srclist.get(0),dest);
		Thread.sleep(6000);
		WebElement table = driver.findElement(By.xpath("//div[contains(text(),'Ms Products')]"));
		createFilterForDomainTable(driver,"Ms Products");
	}
}
