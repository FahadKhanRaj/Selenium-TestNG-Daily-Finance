package page;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LoginPage {
    @FindBy(id="email")
    public WebElement txtEmail;
    @FindBy(id="password")
    public WebElement txtPassword;
    @FindBy(tagName = "button")
    WebElement btnLogin;

    @FindBy(css="[type=button]")
    List<WebElement> btnProfileIcon;
    @FindBy(css = "[role=menuitem]")
    List<WebElement> menuItem;

    @FindBy(tagName = "button")
    public List<WebElement> button;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public void doLogin(String email, String password){
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnLogin.click();
    }
    public void doLogout(){
        btnProfileIcon.get(0).click();
        menuItem.get(1).click();
    }
}
