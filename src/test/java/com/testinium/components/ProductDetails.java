package com.testinium.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetails {

    @FindBy(css = "div.pr-in-w .prc-slg")
    WebElement discountedPrice;

    @FindBy(css = "div.pr-in-w h1.pr-new-br span")
    WebElement productName;

    @FindBy(css = ".pr-in-btn.add-to-bs")
    WebElement addToBasketButton;

    public String getProductName(){
        return productName.getText();
    }

    public String getDiscountedPrice(){
        return discountedPrice.getText();
    }

    public void addToBasket(){
        addToBasketButton.click();
    }
}
