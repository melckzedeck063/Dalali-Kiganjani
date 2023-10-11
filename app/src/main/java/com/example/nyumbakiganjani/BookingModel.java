package com.example.nyumbakiganjani;

public class BookingModel {
   private String property_name,book_status,user,price,date_booked;
   private int property_id,booking_id,customer_id,property_owner_id;

    public BookingModel(){}

    public BookingModel(String property_name, String book_status, String date_booked, String user, String price, int property_id, int booking_id, int customer_id, int property_owner_id) {
        this.property_name = property_name;
        this.book_status = book_status;
        this.date_booked = date_booked;
        this.user = user;
        this.price = price;
        this.property_id = property_id;
        this.booking_id = booking_id;
        this.customer_id = customer_id;
        this.property_owner_id = property_owner_id;
    }

    public String getProperty_name() {
        return property_name;
    }

    public void setProperty_name(String property_name) {
        this.property_name = property_name;
    }

    public String getBook_status() {
        return book_status;
    }

    public void setBook_status(String book_status) {
        this.book_status = book_status;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getProperty_id() {
        return property_id;
    }

    public void setProperty_id(int property_id) {
        this.property_id = property_id;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getProperty_owner_id() {
        return property_owner_id;
    }

    public void setProperty_owner_id(int property_owner_id) {
        this.property_owner_id = property_owner_id;
    }

    public String getDate_booked() {
        return date_booked;
    }

    public void setDate_booked(String date_booked) {
        this.date_booked = date_booked;
    }
}
