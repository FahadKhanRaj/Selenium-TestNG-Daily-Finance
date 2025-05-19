package testrunner;

import config.Setup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import page.CostPage;
import org.openqa.selenium.Alert;
import static config.Setup.driver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class CostTestRunner extends Setup {

    @Test
    public void addNewCost() throws InterruptedException {
        CostPage page = new CostPage();

        WebElement addBtn = driver.findElement(page.addCostButton);
        addBtn.click();

        WebElement nameField = driver.findElement(page.itemName);
        nameField.sendKeys("Money1");

        WebElement amountField = driver.findElement(page.amount);
        amountField.sendKeys("500");

        WebElement dateField = driver.findElement(page.purchaseDate);
        dateField.sendKeys("05042025"); // or "2025-05-04" if required

        WebElement monthDropdown = driver.findElement(page.monthDropdown);
        monthDropdown.sendKeys("May");

        WebElement remarksArea = driver.findElement(page.remarks);
        remarksArea.sendKeys("This is a test remark from runner.");

        driver.findElement(page.submitButton).click();


        Thread.sleep(3000);
        Alert alert = driver.switchTo().alert(); // accpet and click ok
        alert.accept();


    }
}
