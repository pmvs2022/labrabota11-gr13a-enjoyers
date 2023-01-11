package com.example.cpptest;

public class Shop {
    private int addressID;
    private int imageID;

    public int getAddressID() {
        return addressID;
    }

    public int getImageID() {
        return imageID;
    }

    public Shop (int addressID, int imageID) {
        this.addressID = addressID;
        this.imageID = imageID;
    }

}
