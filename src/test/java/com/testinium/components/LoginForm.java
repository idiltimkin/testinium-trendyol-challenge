package com.testinium.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginForm {

    @FindBy(id = "login-email")
    private WebElement emailInput;

    @FindBy(id = "login-password-input")
    private WebElement passwordInput;

    @FindBy(className = "submit")
    private WebElement loginButton;

    public void login(String userName, String password) {
        emailInput.sendKeys(userName);
        passwordInput.sendKeys(password);

        loginButton.submit();
    }
}
