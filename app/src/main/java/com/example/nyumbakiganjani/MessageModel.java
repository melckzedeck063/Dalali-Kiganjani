package com.example.nyumbakiganjani;

public class MessageModel {
     int user_id,receiver_id;
     String message,time;

    public MessageModel() {
    }

    public MessageModel(int user_id, int receiver_id, String message, String time) {
        this.user_id = user_id;
        this.receiver_id = receiver_id;
        this.message = message;
        this.time = time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(int receiver_id) {
        this.receiver_id = receiver_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
