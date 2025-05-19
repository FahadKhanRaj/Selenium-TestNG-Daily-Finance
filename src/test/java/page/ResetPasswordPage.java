package page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ResetPasswordPage {

    @FindBy(css="[type=email]")
    public WebElement txtEmail;

    @FindBy(css="[type=submit]")
    WebElement btnSumbit;

    @FindBy(tagName = "input")
    List<WebElement> txtInput;

    @FindBy(tagName = "button")
    WebElement btnReset;

        WebDriver driver;
        // Constructor

    public ResetPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Important for @FindBy
    }

        // Locator of "Reset it here" link
        By resetPasswordLink = By.cssSelector("a[href='/forgot-password']");

        // Action method
        public void clickResetPasswordLink() {
            driver.findElement(resetPasswordLink).click();

        }

    public void enterEmail(String email) {
        txtEmail.clear();
        txtEmail.sendKeys(email);
    }

    public void clickSubmit() {
        btnSumbit.click();
    }
    }


