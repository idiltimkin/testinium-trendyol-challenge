package com.testinium.tests;

import com.testinium.components.*;
import com.testinium.pages.BasketPage;
import com.testinium.util.Navigator;
import com.testinium.pages.HomePage;
import com.testinium.pages.LoginPage;
import com.testinium.util.Credentials;
import com.testinium.util.FileUtil;
import com.testinium.util.WaitUtil;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.PageFactory.initElements;

@ExtendWith(SeleniumJupiter.class)
public class SearchBasketTest {
    Credentials credentials;
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    Navigator navigator;
    BasketPage basketPage;
    WaitUtil waitUtil;

    SearchBasketTest(WebDriver driver) {
        this.driver = driver;
        credentials = Credentials.getInstance();
        homePage = initElements(driver, HomePage.class);
        loginPage = initElements(driver, LoginPage.class);
        navigator = initElements(driver, Navigator.class);
        basketPage = initElements(driver, BasketPage.class);
        waitUtil = initElements(driver, WaitUtil.class);
    }

    @ParameterizedTest
    @Execution(ExecutionMode.SAME_THREAD)
    @ValueSource(strings = {"bilgisayar"})
    void search_and_add_to_basket(String searchTerm) {
        LoginForm loginForm = initElements(driver, LoginForm.class);
        SearchBox searchBox = initElements(driver, SearchBox.class);
        SearchResult searchResult = initElements(driver, SearchResult.class);
        ProductDetails productDetails = initElements(driver, ProductDetails.class);
        Basket basket = initElements(driver, Basket.class);
        MyAccount myAccount = initElements(driver, MyAccount.class);

        navigator.navigateTo(homePage);
        assertEquals(homePage.url(), driver.getCurrentUrl());

        navigator.navigateTo(loginPage);
        loginForm.login(credentials.getEmail(), credentials.getPassword());
        assertEquals(credentials.getEmail(), myAccount.currentUser());

        searchBox.search(searchTerm);
        searchResult.clickRandomProduct();

        String productDetailsPrice = productDetails.getDiscountedPrice();
        logProductDetails(productDetails.getProductName(), productDetailsPrice);
        productDetails.addToBasket();

        navigator.navigateTo(basketPage);

        String basketPrice = basket.getTotalPrice();
        assertEquals(productDetailsPrice, basketPrice);

        basket.increment(0);
        waitUtil.waitAjax();
        assertEquals(2, basket.itemCount(0));

        basket.remove(0);
        basket.confirmRemove();
        waitUtil.waitAjax();

        assertTrue(basket.isBasketEmpty());
    }

    private void logProductDetails(String productName, String productDetailsPrice){
        String content = String.format("%s - %s", productName, productDetailsPrice);
        FileUtil.write("logs/product.txt", content);
    }
}
