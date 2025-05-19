package config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailMessage {
    private String id;
    private String threadId;
    private String[] labelIds;
    private String snippet;
    private Payload payload;

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getThreadId() { return threadId; }
    public void setThreadId(String threadId) { this.threadId = threadId; }

    public String[] getLabelIds() { return labelIds; }
    public void setLabelIds(String[] labelIds) { this.labelIds = labelIds; }

    public String getSnippet() { return snippet; }
    public void setSnippet(String snippet) { this.snippet = snippet; }

    public Payload getPayload() { return payload; }
    public void setPayload(Payload payload) { this.payload = payload; }

    public static class Payload {
        private Header[] headers;

        public Header[] getHeaders() { return headers; }
        public void setHeaders(Header[] headers) { this.headers = headers; }
    }

    public static class Header {
        private String name;
        private String value;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getValue() { return value; }
        public void setValue(String value) { this.value = value; }
    }
}