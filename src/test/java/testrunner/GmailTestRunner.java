package testrunner;

import config.EmailMessage;
import page.GmailService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.cdimascio.dotenv.Dotenv;


public class GmailTestRunner {
    private GmailService gmailService;
    private static final String EXPECTED_SNIPPET = "Welcome to our platform! We&#39;re excited to have you onboard. Best regards, Road to Career";

    @BeforeClass
    public void setup() {
        Dotenv dotenv = Dotenv.load(); // Load from .env
        String accessToken = dotenv.get("ACCESS_TOKEN"); // Get token
        gmailService = new GmailService(accessToken);
//

    }

    @Test
    public void testSecondEmailSnippet() {
        try {
            EmailMessage secondEmail = gmailService.getSecondEmail();
            System.out.println("Second Email Snippet: " + secondEmail.getSnippet());

            // Single assertion for snippet content
            Assert.assertTrue(secondEmail.getSnippet().contains(EXPECTED_SNIPPET),
                    "Snippet does not contain expected text. Actual: " + secondEmail.getSnippet());

        } catch (Exception e) {
            Assert.fail("Test failed: " + e.getMessage());
        }
    }
    @Test
    public void testPasswordResetEmailSnippet() {
        try {
            EmailMessage secondEmail = gmailService.getSecondEmail();
            String snippet = secondEmail.getSnippet();
            System.out.println("Snippet (password reset): " + snippet);

            String expectedResetSnippet = "Click on the following link to reset your password";

            Assert.assertTrue(snippet.contains(expectedResetSnippet),
                    "Password reset snippet not found. Actual: " + snippet);

        } catch (Exception e) {
            Assert.fail("Password reset email test failed: " + e.getMessage());
        }
    }

    @Test
    public void testResetPasswordEmailIsReceived() {
        try {
            String expectedResetSnippet = "Click on the following link to reset your password";
            EmailMessage email = gmailService.findEmailBySnippetText(expectedResetSnippet);

            System.out.println("✅ Reset Email Snippet Found: " + email.getSnippet());
            Assert.assertTrue(email.getSnippet().contains(expectedResetSnippet));

        } catch (Exception e) {
            Assert.fail("❌ Password reset email not found: " + e.getMessage());
        }
    }


}