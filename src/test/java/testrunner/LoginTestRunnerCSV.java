package testrunner;

import config.LoginDataSet;
import config.Setup;
import org.testng.annotations.Test;
import page.LoginPage;

public class LoginTestRunnerCSV extends Setup {
    @Test(dataProvider = "LoginCSVData", dataProviderClass = LoginDataSet.class)
    public void doLogin(String email, String password){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogin(email,password);
        loginPage.doLogout();
    }
}
