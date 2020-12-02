package com.splashbi.pageelement;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class ElementLocator {
	static Logger logger = Logger.getLogger(ElementLocator.class);
	public static By getLocator(String element){
		
		String locator = element.split(":",2)[0].trim();
		String expression = element.split(":",2)[1].trim();
		WebElement we = null;
		String s;
		By object = null;
	try {
	    switch(locator)
		{
			case "xpath":
				object = By.xpath(expression);
				break;
		
		    case "id":
			    object = By.id(expression);
			    break;
			    
			    
		    case "name":
			    object = By.name(expression);
			    break;
			    
		    case "linktext":
			    object = By.linkText(expression);
			    break;
			    
		    case "partiallinktext":
			    object = By.partialLinkText(expression);
			    break;
			    
		    case "classname":
			    object = By.className(expression);
			    break;  
			
			default:
				System.out.println("Wrong locator value received");
			    
	     }
	}catch(Exception e) {
		logger.error("Error in getting webelement:" + e.getMessage());
	}
    return object;
}
	
	public static By getDynamicLocator(String xpathexpression,String object){
		By loc = null;
		String expression = xpathexpression.replaceAll("dynamic", object);
		System.out.println(expression);
		loc = By.xpath(expression);
		return loc;
		
	}
	
	public static void main(String args[]) {
		
		getDynamicLocator("//div[@title='dynamic']//ul//li[@title='Filters']","Tests517");
	}

}
