package com.example.nyumbakiganjani;

public class Property {
    String property, price, duration, location,description;
    int user_id, image,rooms;

    public Property(){

    }

    public Property(String property, String price, String duration, String location, String description, int user_id, int image, int rooms) {
        this.property = property;
        this.price = price;
        this.duration = duration;
        this.location = location;
        this.description = description;
        this.user_id = user_id;
        this.image = image;
        this.rooms = rooms;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }
}
