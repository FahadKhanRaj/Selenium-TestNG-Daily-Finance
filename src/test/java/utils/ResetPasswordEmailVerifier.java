package utils;

import config.EmailMessage;
import utils.ResetPasswordLink;
import page.GmailService;

public class ResetPasswordEmailVerifier {

    private final GmailService gmailService;

    public ResetPasswordEmailVerifier(String accessToken) {
        this.gmailService = new GmailService(accessToken);
    }

    public boolean isResetLinkEmailReceived() {
        try {
            EmailMessage email = gmailService.getFirstEmail();
            String snippet = email.getSnippet();
            System.out.println("Email Snippet: " + snippet);

            String prefix = "Click on the following link to reset your password: ";
            if (snippet.contains(prefix)) {
                String extractedLink = snippet.substring(snippet.indexOf(prefix) + prefix.length()).trim();
                ResetPasswordLink.resetLink = extractedLink;
                System.out.println("Extracted Reset Link: " + extractedLink);
                return true;
            }

            return false;
        } catch (Exception e) {
            System.out.println("Failed to verify reset email: " + e.getMessage());
            return false;
        }
    }
}
