package testrunner;

import config.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LogoutLoginPage;

public class LogoutLoginTestRunner extends Setup {

    @Test
    public void testLogoutAndLogin() throws InterruptedException {
        LogoutLoginPage page = new LogoutLoginPage(driver);

        page.clickProfileOption();
        page.clickLogout();

        Thread.sleep(2000);

        // Try login with incorrect email first
        page.enterEmail("fahadkhanraj1111@gmail.com");  // incorrect email
        page.enterPassword("123");
        page.clickLoginButton();

        Thread.sleep(2000);

        String errorMessage = page.getLoginErrorMessage();
        Assert.assertEquals(errorMessage, "Invalid email or password", "Error message mismatch!");

        // Now login with correct email
        Thread.sleep(2000);

        WebElement emailField = driver.findElement(By.id("email"));
        Actions actions = new Actions(driver);
        actions.click(emailField)
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.DELETE)
                .perform();

        WebElement passwordField = driver.findElement(By.id("password"));
        actions.click(passwordField)
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.DELETE)
                .perform();



        Thread.sleep(2000);
        page.enterEmail("fahadkhanraj1111+11@gmail.com");  // correct email
        page.enterPassword("123");
        page.clickLoginButton();

        Thread.sleep(2000);


        page.clickProfileOption();
        page.clickLogout();

        Thread.sleep(2000);

//        Assert.assertTrue(page.isLoginSuccessful(), "Login was not successful");
    }
}
