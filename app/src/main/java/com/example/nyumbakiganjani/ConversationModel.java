package com.example.nyumbakiganjani;

public class ConversationModel {
    String user_firstname="use_firstname",user_lastname="user_lastname",  message="message",time="time";
    int conversation_id,receiver_id;

    public ConversationModel(){

    }

    public ConversationModel(String user_firstname, String user_lastname, String message, String time, int conversation_id,int receiver_id) {
        this.user_firstname = user_firstname;
        this.user_lastname = user_lastname;
        this.message = message;
        this.time = time;
        this.conversation_id = conversation_id;
        this.receiver_id = receiver_id;
    }

    public String getUser_firstname() {
        return user_firstname;
    }

    public void setUser_firstname(String user_firstname) {
        this.user_firstname = user_firstname;
    }

    public String getUser_lastname() {
        return user_lastname;
    }

    public void setUser_lastname(String user_lastname) {
        this.user_lastname = user_lastname;
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

    public int getConversation_id() {
        return conversation_id;
    }

    public void setConversation_id(int conversation_id) {
        this.conversation_id = conversation_id;
    }

    public int getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(int receiver_id) {
        this.receiver_id = receiver_id;
    }
}
