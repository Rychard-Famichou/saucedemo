package com.saucedemo.automation;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends Base {
    @Test
    public void t01standardUserLogin() {
        login(STANDARD_USER, PASSWORD);
        Assert.assertTrue(isElementDisplayed("#inventory_container"));
        logout();
    }

    @Test
    public void t02lockedOutUserLogin() {
        login(LOCKED_OUT_USER, PASSWORD);
        checkLockedUser();
        driver.navigate().refresh();
    }

    @Test
    public void t03problemUserLogin() {
        login(PROBLEM_USER, PASSWORD);
        Assert.assertTrue(isElementDisplayed("#inventory_container"));
        logout();
    }

    @Test
    public void t04performanceGlitchUserLogin() {
        login(PERFORMANCE_GLITCH_USER, PASSWORD);
        Assert.assertTrue(isElementDisplayed("#inventory_container"));
        logout();
    }

    @Test
    public void t05randomUserNameLoginToString() {
        login(random.toString(), PASSWORD);
        checkErrorUser();
        driver.navigate().refresh();
    }

    @Test
    public void t06randomPasswordLoginToString() {
        login(STANDARD_USER, random.toString());
        checkErrorUser();
        driver.navigate().refresh();
    }
}