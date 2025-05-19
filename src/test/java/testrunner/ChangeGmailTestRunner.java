package testrunner;

import org.testng.annotations.Test;
import page.ChangeGmailPage;
import config.Setup;

public class ChangeGmailTestRunner extends Setup {

    @Test
    public void runGmailChangeTest() throws InterruptedException {
        ChangeGmailPage page = new ChangeGmailPage(driver);

        page.clickAccountIcon();
        page.clickProfileOption();
        Thread.sleep(2000);
        page.clickEditButton();
        Thread.sleep(2000);
        page.updateEmail("fahadkhanraj1111+11@gmail.com");
        page.clickUpdateButton();
        Thread.sleep(2000);
        page.acceptAlert();
    }
}
