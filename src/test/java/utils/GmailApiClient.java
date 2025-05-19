package utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class GmailApiClient {
    private static final String BASE_URL = "https://gmail.googleapis.com/gmail/v1/users/me";
    private final String accessToken;

    public GmailApiClient(String accessToken) {
        this.accessToken = accessToken.trim();
    }

    public String getEmailList() throws Exception {
        HttpResponse response = executeRequest("/messages");
        return handleResponse(response);
    }

    public String getEmailDetails(String messageId) throws Exception {
        HttpResponse response = executeRequest("/messages/" + messageId);
        return handleResponse(response);
    }

    private HttpResponse executeRequest(String endpoint) throws Exception {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(BASE_URL + endpoint);
        request.addHeader("Authorization", "Bearer " + accessToken);
        request.addHeader("Accept", "application/json");
        return client.execute(request);
    }

    private String handleResponse(HttpResponse response) throws Exception {
        int statusCode = response.getStatusLine().getStatusCode();
        String responseBody = EntityUtils.toString(response.getEntity());

        if (statusCode != 200) {
            throw new Exception("API Error " + statusCode + ": " + responseBody);
        }
        return responseBody;
    }
}