package edu.co.icesi.firestoreejemplo;

public class Message {
    private String id;
    private String message;
    private String userID;
    private long date;

    public Message() {
    }

    public Message(String id, String message, String userID, long date) {
        this.id = id;
        this.message = message;
        this.userID = userID;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
