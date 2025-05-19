package testrunner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.AdminPage;
import page.LoginPage;
import config.Setup;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static config.Setup.driver;

public class AdminLoginTestRunner extends Setup {

    @Test(priority =1,description = "admin login")

    public void adminLogin() {

        driver.get("https://dailyfinance.roadtocareer.net/login");

        LoginPage loginPage = new LoginPage(driver);
        if (System.getProperty("email") != null && System.getProperty("password") != null) {
            loginPage.doLogin(System.getProperty("email"), System.getProperty("password"));
        } else {
            loginPage.doLogin("admin@test.com", "admin123");
        }
    }

    @Test(priority = 2, description = "Search by gmail")
    public void searchUpdatedGmail() {
        AdminPage adminPage = new AdminPage(driver);

        String email = "fahadkhanraj1111+11@gmail.com";

        // Type the email into the search input
        adminPage.searchInput.sendKeys(email);

        // Fetch the first row's third column (email column) text
        List<WebElement> allData = driver.findElements(By.xpath("//tbody/tr[1]/td"));
        String actualEmail = allData.get(2).getText();

        // Assert that the email in the table matches the one we searched
        Assert.assertTrue(actualEmail.contains(email), "Email not found in the table!");

        // Logout at the end
        LoginPage logOut = new LoginPage(driver);
        logOut.doLogout();
    }
    }
