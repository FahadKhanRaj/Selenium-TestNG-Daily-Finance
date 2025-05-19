package testrunner;

import config.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.ResetPasswordPage;
import utils.ResetPasswordEmailVerifier;


import java.time.Duration;

import io.github.cdimascio.dotenv.Dotenv;


public class ResetPasswordTestRunner extends Setup {

    @Test(description = "Verify clicking reset password link")
    public void resetPasswordTest() throws InterruptedException {
        driver.get("https://dailyfinance.roadtocareer.net/");

        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='/forgot-password']")));
        resetPasswordPage.clickResetPasswordLink();

        // -------------- reset password page:
        By emailField = By.cssSelector("input[type='email']");
        By sendResetLinkButton = By.cssSelector("button[type='submit']");
        Thread.sleep(2000);
    }

    // Negative Test Case 1: Invalid Email Format (without @)
    @Test(priority = 2, description = "Negative Case: Invalid Email Format (without @)")
    public void invalidEmailFormat() throws InterruptedException {
        By emailField = By.cssSelector("input[type='email']");
        By sendResetLinkButton = By.cssSelector("button[type='submit']");

        driver.findElement(emailField).clear();

        String invalidEmail = "akofs";
        driver.findElement(emailField).sendKeys(invalidEmail);
        driver.findElement(sendResetLinkButton).click();

        String validationMsg = driver.findElement(emailField).getAttribute("validationMessage");
        String expectedMsg = "Please include an '@' in the email address. '" + invalidEmail + "' is missing an '@'.";
        System.out.println(validationMsg);
        Assert.assertTrue(validationMsg.contains(expectedMsg));
    }

    // Test Case: Email Not Registered
    @Test(priority = 3, description = "Negative Case: Email Not Registered")
    public void emailNotRegistered() throws InterruptedException {
        driver.get("https://dailyfinance.roadtocareer.net/");

        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/forgot-password']")));
        resetPasswordPage.clickResetPasswordLink();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']")));

        By emailField = By.cssSelector("input[type='email']");
        By sendResetLinkButton = By.cssSelector("button[type='submit']");

        driver.findElement(emailField).clear();
        String unregisteredEmail = "akofsed@gaosk.com";
        driver.findElement(emailField).sendKeys(unregisteredEmail);
        driver.findElement(sendResetLinkButton).click();

        WebDriverWait waitError = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorMessage = waitError.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".MuiTypography-root.MuiTypography-body1.css-gjwoc1")));

        String actualMessage = errorMessage.getText();
        String expectedMessage = "Your email is not registered";
        System.out.println(actualMessage);
        Assert.assertEquals(actualMessage, expectedMessage);
    }





    @Test(priority = 4, description = "Positive Case: Send Reset Email and Verify Gmail Snippet")
    public void sendResetLinkAndVerifyGmail() throws InterruptedException {
        driver.get("https://dailyfinance.roadtocareer.net/");
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/forgot-password']")));

        resetPasswordPage.clickResetPasswordLink();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']")));

        String testEmail = "fahadkhanraj1111@gmail.com";
        resetPasswordPage.enterEmail(testEmail);
        resetPasswordPage.clickSubmit();

        Thread.sleep(5000); // Optional: wait for the email to arrive

        // Now verify Gmail
        Dotenv dotenv = Dotenv.load();
        String accessToken = dotenv.get("ACCESS_TOKEN");
        ResetPasswordEmailVerifier verifier = new ResetPasswordEmailVerifier(accessToken);

        Assert.assertTrue(verifier.isResetLinkEmailReceived(), "Reset email not received or incorrect content");
    }

}
