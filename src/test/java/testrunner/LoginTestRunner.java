package testrunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LoginPage;
import utils.Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoginTestRunner extends Setup {
    @Test(priority = 1, description = "Admin tries with wrong creds", enabled = false)
    public void loginWithWrongCreds(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogin("admin@test.com","wrongcreds");
        String messageActual= driver.findElement(By.tagName("p")).getText();
        String messageExpected="Invalid email or password";
        Assert.assertEquals(messageActual,messageExpected);
        clearData();
    }
    public void clearData(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.txtEmail.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
        loginPage.txtPassword.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
    }
    @Test(priority = 2, description = "Admin user login", groups = "smoke")
    public void adminLogin() throws IOException {
        LoginPage loginPage=new LoginPage(driver);
        if(System.getProperty("email")!=null && System.getProperty("password")!=null){
            loginPage.doLogin(System.getProperty("email"),System.getProperty("password"));
        }
        else{
            loginPage.doLogin("admin@test.com","admin123");
        }
        Utils.getToken(driver);
    }
    @Test (priority = 3, description = "Admin logout", groups = "smoke", enabled = false)
    public void logout(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogout();
    }
    @Test (priority = 4, description = "User Login", groups = "smoke", enabled = false)
    public void userLogin() throws IOException, ParseException {
        JSONParser parser=new JSONParser();
        JSONArray jsonArray= (JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject userObj= (JSONObject) jsonArray.get(jsonArray.size()-1);
        String email=userObj.get("email").toString();
        String password=userObj.get("password").toString();

        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogin(email,password);
    }
}
