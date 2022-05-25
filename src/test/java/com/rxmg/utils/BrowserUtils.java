package com.rxmg.utils;

import org.openqa.selenium.WebElement;

public class BrowserUtils {

    /**
     * This method is to provide stability to the selenium Webdriver. It check for the element to be displayed prior to the action
     * @param element
     * @throws Exception
     */
    public static void clickByElement(WebElement element) throws Exception {
        if (element.isDisplayed()){
            element.click();
        }else {
            throw new Exception("Element is not displayed unable to click");
        }
    }
}

