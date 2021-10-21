package models;

import java.sql.Timestamp;

public class Comment {
    
    private int id;
    private String message;
    private Timestamp timestamp;
    private String userEmail;
    private int requestId;
    
    public Comment(int id, String message, Timestamp timestamp, String userEmail, int requestId) {
        this.id = id;
        this.message = message;
        this.timestamp = timestamp;
        this.userEmail = userEmail;
        this.requestId = requestId;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Timestamp getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public int getRequestId() {
        return requestId;
    }
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }
    
}
