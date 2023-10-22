package com.example.nyumbakiganjani;

public class Property {
    String property, price, duration, location,description, bathrooms,bedrooms,parking,status;
    int user_id,property_ID;
    String coverPhoto;

    public Property(){

    }

    public Property(String property, String location, String price, String bedrooms, String bathrooms, String parking, String duration, String coverPhoto, String description,String status, int user_id , int property_ID) {
        this.property = property;
        this.price = price;
        this.duration = duration;
        this.location = location;
        this.description = description;
        this.user_id = user_id;
        this.parking = parking;
        this.bedrooms = bedrooms;
        this.bathrooms  = bathrooms;
        this.coverPhoto  = coverPhoto;
        this.status = status;
        this.property_ID = property_ID;
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

    public int getProperty_ID() {
        return property_ID;
    }

    public void setProperty_ID(int property_ID) {
        this.property_ID = property_ID;
    }
    //    public int getImage() {
//        return image;
//    }
//
//    public void setImage(int image) {
//        this.image = image;
//    }

//    public int getRooms() {
//        return rooms;
//    }
//
//    public void setRooms(int rooms) {
//        this.rooms = rooms;
//    }

    public String getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(String bathrooms) {
        this.bathrooms = bathrooms;
    }

    public String getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(String bedrooms) {
        this.bedrooms = bedrooms;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
