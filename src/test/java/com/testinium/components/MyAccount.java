package com.testinium.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccount {
    @FindBy(className = "user-email")
    private WebElement currentUser;

    public String currentUser() {
        return currentUser.getAttribute("textContent");
    }

}
