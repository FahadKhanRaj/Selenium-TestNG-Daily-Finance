package page;

import config.EmailMessage;
import config.EnvConfig;
import utils.GmailApiClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;

public class GmailService {
    private String accessToken;

    private final GmailApiClient apiClient;
    private final ObjectMapper objectMapper;

    public GmailService(String accessToken) {
        this.accessToken = EnvConfig.get("ACCESS_TOKEN");

        this.apiClient = new GmailApiClient(accessToken);
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public EmailMessage getSecondEmail() throws Exception {
        String listResponse = apiClient.getEmailList();
        EmailListResponse emails = objectMapper.readValue(listResponse, EmailListResponse.class);

        if (emails.messages == null || emails.messages.length < 2) {
            throw new Exception("Minimum 2 emails required. Found: " +
                    (emails.messages != null ? emails.messages.length : 0));
        }

        String secondEmailId = emails.messages[1].id;
        String emailDetails = apiClient.getEmailDetails(secondEmailId);
        return objectMapper.readValue(emailDetails, EmailMessage.class);
    }

    public EmailMessage getFirstEmail() throws Exception {
        String listResponse = apiClient.getEmailList();
        EmailListResponse emails = objectMapper.readValue(listResponse, EmailListResponse.class);

        if (emails.messages == null || emails.messages.length < 2) {
            throw new Exception("Minimum 2 emails required. Found: " +
                    (emails.messages != null ? emails.messages.length : 0));
        }

        String secondEmailId = emails.messages[0].id;
        String emailDetails = apiClient.getEmailDetails(secondEmailId);
        return objectMapper.readValue(emailDetails, EmailMessage.class);
    }


    private static class EmailListResponse {
        public EmailMetadata[] messages;
        public String nextPageToken;
    }

    private static class EmailMetadata {
        public String id;
        public String threadId;
    }

    public EmailMessage findEmailBySnippetText(String expectedText) throws Exception {
        String listResponse = apiClient.getEmailList();
        EmailListResponse emails = objectMapper.readValue(listResponse, EmailListResponse.class);

        if (emails.messages == null || emails.messages.length == 0) {
            throw new Exception("No emails found in inbox.");
        }

        for (EmailMetadata message : emails.messages) {
            String emailDetails = apiClient.getEmailDetails(message.id);
            EmailMessage email = objectMapper.readValue(emailDetails, EmailMessage.class);

            if (email.getSnippet() != null && email.getSnippet().contains(expectedText)) {
                return email;
            }
        }

        throw new Exception("No email found with snippet containing: \"" + expectedText + "\"");
    }

}