package testrunner;

import config.Setup;
import utils.ResetPasswordLink;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class ResetWithNewPasswordTestRunner extends Setup {

    public static String newPass;

    @BeforeClass
    public void setupEnv() {
        Dotenv dotenv = Dotenv.load();
        newPass = dotenv.get("PASSWORD");
    }

    @Test
    public void completeResetPasswordFlow() throws InterruptedException {
        System.out.println("Driver is: .................." + driver);

        driver.get(ResetPasswordLink.resetLink);  // Use extracted link

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Fill password fields using the newPass variable
        By passwordField1 = By.cssSelector("input[id=':r0:']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField1));
        driver.findElement(passwordField1).sendKeys(newPass);

        By passwordField2 = By.cssSelector("input[id=':r1:']");
        driver.findElement(passwordField2).sendKeys(newPass);

        // Click Reset Password button
        By resetButton = By.cssSelector("button[type='submit']");
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(resetButton));
        button.click();
    }
}
