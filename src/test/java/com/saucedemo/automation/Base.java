package com.saucedemo.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.Random;

public class Base {
    WebDriver driver = new ChromeDriver();

    String URL = "https://www.saucedemo.com/";
    final String STANDARD_USER = "standard_user";
    final String LOCKED_OUT_USER = "locked_out_user";
    final String PROBLEM_USER = "problem_user";
    final String PERFORMANCE_GLITCH_USER = "performance_glitch_user";
    String PASSWORD = "secret_sauce";
    final Random random = new Random();

    @BeforeClass
    public void setUp() {
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public void login(String userName, String password) {
        fillField(userName, By.cssSelector("#user-name"));
        fillField(password, By.cssSelector("#password"));
        driver.findElement(By.cssSelector("#login-button")).click();
    }

    private void fillField(String userName, By locator) {
        driver.findElement(locator).click();
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(userName);
    }

    public boolean isElementDisplayed(String cssLocator) {
        return driver.findElement(By.cssSelector(cssLocator)).isDisplayed();
    }

    public void logout() {
        driver.findElement(By.cssSelector("#react-burger-menu-btn")).click();
        driver.findElement(By.cssSelector("#logout_sidebar_link")).click();
        Assert.assertTrue(isElementDisplayed("#login-button"));
    }

    public void checkLockedUser() {
        String lockedExpected = "Epic sadface: Sorry, this user has been locked out.";
        WebElement lockedActual = driver.findElement(By.cssSelector("h3[data-test='error']"));
        Assert.assertEquals(lockedActual.getText(), lockedExpected);
        ;
    }

    public void checkErrorUser() {
        String errorExpected = "Epic sadface: Username and password do not match any user in this service";
        WebElement errorActual = driver.findElement(By.cssSelector("h3[data-test='error']"));
        Assert.assertEquals(errorActual.getText(), errorExpected);
        ;
    }
}
