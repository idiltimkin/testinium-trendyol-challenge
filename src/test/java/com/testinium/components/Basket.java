package com.testinium.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Basket {

    @FindBy(css = "dd.total-price")
    WebElement totalPrice;

    @FindAll({ @FindBy(className = "pb-basket-item") })
    List<WebElement> basketItems;

    @FindBy(css = "button[ng-click^='removeItemAs']")
    WebElement itemRemoveConfirmation;

    @FindBy(css = "#basketNoProductPage")
    WebElement emptyBasketSection;

    public String getTotalPrice(){
        return totalPrice.getText();
    }

    public void increment(int order){
        basketItems.get(order).findElements(By.className("ty-numeric-counter-button")).get(1).click();
    }

    public void decrement(int order){
        basketItems.get(order).findElements(By.className("ty-numeric-counter-button")).get(0).click();
    }

    public void remove(int order){
        basketItems.get(order).findElement(By.className("i-trash")).click();
    }

    public Integer itemCount(int order){
        String count = basketItems.get(order).findElement(By.cssSelector("input.counter-content")).getAttribute("value");
        return Integer.valueOf(count);
    }

    public void confirmRemove(){
        itemRemoveConfirmation.click();
    }

    public boolean isBasketEmpty(){
        return emptyBasketSection.isDisplayed();
    }
}
