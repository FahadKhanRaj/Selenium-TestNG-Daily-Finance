package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogoutLoginPage {
    WebDriver driver;

    public LogoutLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to click the profile icon (SVG) to open the dropdown
    public void clickProfileIcon() {
        driver.findElement(By.cssSelector("svg[data-testid='AccountCircleIcon']")).click();
    }

    // Method to click the 'Profile' option after opening the dropdown
    public void clickProfileOption() {
        clickProfileIcon(); // Open the dropdown first

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement profileOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[normalize-space()='Profile']")));
        profileOption.click();
    }

    // Method to click the 'Logout' option
    public void clickLogout() {
        // First click the profile icon to make sure the menu is visible
        clickProfileIcon();

        // Wait for the Logout option to be clickable and then click it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement logoutOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[normalize-space()='Logout']")));
        logoutOption.click();
    }


    public void enterEmail(String email) {
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
    }

    public boolean isLoginSuccessful() {
        return driver.getPageSource().contains("Profile");
    }

    public String getLoginErrorMessage() {
        return driver.findElement(By.cssSelector("p.MuiTypography-root.MuiTypography-body1.css-xv13ao")).getText();
    }
}
