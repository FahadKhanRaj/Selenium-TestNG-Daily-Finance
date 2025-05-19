package testrunner;

import config.Setup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.CostPage;
import org.openqa.selenium.Alert;
import static config.Setup.driver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class CostTestRunner2 extends Setup {

    @Test
    public void addNewCost() throws InterruptedException {
        CostPage page = new CostPage();

        WebElement addBtn = driver.findElement(page.addCostButton);
        addBtn.click();

        WebElement nameField = driver.findElement(page.itemName);
        nameField.sendKeys("Money2");

        WebElement amountField = driver.findElement(page.amount);
        amountField.sendKeys("1000");

        WebElement dateField = driver.findElement(page.purchaseDate);
        dateField.sendKeys("05042025"); // or "2025-05-04" if required

        WebElement monthDropdown = driver.findElement(page.monthDropdown);
        monthDropdown.sendKeys("May");

        driver.findElement(page.submitButton).click();


        Thread.sleep(3000);
        Alert alert = driver.switchTo().alert(); // accpet and click ok
        alert.accept();

        Thread.sleep(1000);

        //assertion:

        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains("<td>Money1</td>"), "Money1 not found.");
        Assert.assertTrue(pageSource.contains("<td>Money2</td>"), "Money2 not found");
    }
}
