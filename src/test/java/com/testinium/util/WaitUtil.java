package com.testinium.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WaitUtil {
    private final WebDriverWait wait;

    public WaitUtil(WebDriver driver){
        this.wait = new WebDriverWait(driver, 3);
        driver.manage().timeouts()
                .setScriptTimeout(5, TimeUnit.SECONDS)
                .implicitlyWait(5, TimeUnit.SECONDS)
                .pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    public void waitAjax(){
        this.wait.until(ExpectedConditions.invisibilityOfElementLocated((By.className("loadinglightboxgif"))));
    }
}
