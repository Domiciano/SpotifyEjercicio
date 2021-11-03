package edu.co.icesi.firestoreejemplo.fcm;

public class FCMMessage<T> {

    private String to;
    private T data;

    public FCMMessage(String to, T data) {
        this.to = to;
        this.data = data;
    }

    public FCMMessage() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
