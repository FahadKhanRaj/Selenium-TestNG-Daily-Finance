package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ChangeGmailPage {
    WebDriver driver;

    public ChangeGmailPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAccountIcon() {
        driver.findElement(By.cssSelector("svg[data-testid='AccountCircleIcon']")).click();
    }

//    public void clickProfileOption() {
//        driver.findElement(By.linkText("Profile")).click();
//    }

    public void clickProfileOption() {
        driver.findElement(By.xpath("//li[text()='Profile']")).click();
    }


//    public void clickEditButton() {
//        driver.findElement(By.cssSelector("button[data-testid='EditIcon']")).click();
//    }

    public void clickEditButton() {
        driver.findElement(By.xpath("//button[text()='Edit']")).click();
    }


    public void updateEmail(String newEmail) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.name("email")));

        emailField.click(); // focuses the field
        emailField.clear(); // tries to clear
        emailField.sendKeys(Keys.CONTROL + "a", Keys.DELETE); // more aggressive clear
        emailField.sendKeys(newEmail); // type new email
    }

//    public void clickUpdateButton() {
//        driver.findElement(By.cssSelector("button.MuiButton-containedPrimary")).click();
//    }

    public void clickUpdateButton() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//button[normalize-space()='Update']")))
                .perform(); // Scrolls to bring the element into view

        driver.findElement(By.xpath("//button[normalize-space()='Update']")).click();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }
}
