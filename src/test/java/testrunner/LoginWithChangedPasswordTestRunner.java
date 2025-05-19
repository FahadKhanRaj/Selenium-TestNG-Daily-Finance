package testrunner;

import config.Setup;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LoginPage;

public class LoginWithChangedPasswordTestRunner extends Setup {

    @Test(priority = 2, description = "Login with newly changed password")
    public void loginWithNewPassword() {
        // Get the email and password
        String email = "fahadkhanraj1111@gmail.com"; // Replace with your test email
        String password = ResetWithNewPasswordTestRunner.newPass;

        // Initialize LoginPage
        LoginPage loginPage = new LoginPage(driver);

        // Perform login
        loginPage.doLogin(email, password);


        System.out.println("Successfully logged in with new password: " + password);
    }
}