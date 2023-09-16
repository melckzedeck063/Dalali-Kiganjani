package com.example.nyumbakiganjani;

public class ConversationModel {
    String userName="userName", message="message",time="time";

    public ConversationModel(){

    }

    public ConversationModel(String userName, String time) {
        this.userName = userName;
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
