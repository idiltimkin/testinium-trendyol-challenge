package com.testinium.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;
import java.util.Random;

public class SearchResult {

    @FindBy(css = "div.srch-prdcts-cntnr .p-card-wrppr:nth-child(1)")
    WebElement firstProduct;

    @FindBys(@FindBy(css = "div.srch-prdcts-cntnr .p-card-wrppr"))
    List<WebElement> products;

    public void clickRandomProduct(){
        int random = new Random().nextInt(products.size() - 1);
        products.get(random).click();
    }
}
