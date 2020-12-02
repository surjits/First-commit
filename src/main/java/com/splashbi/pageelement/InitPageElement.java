package com.splashbi.pageelement;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public interface InitPageElement {


	public By getBy();
	public By getDynamicBy(String val);
		

}
