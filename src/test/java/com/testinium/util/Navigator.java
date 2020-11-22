package com.testinium.util;

import com.testinium.Page;
import org.openqa.selenium.WebDriver;

public class Navigator {

    private final WebDriver driver;

    public Navigator(WebDriver driver){
        this.driver = driver;
    }

    public void navigateTo(Page page){
        driver.get(page.url());
    }
}
