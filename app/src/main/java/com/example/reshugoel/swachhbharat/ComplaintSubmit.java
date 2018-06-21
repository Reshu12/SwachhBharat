package com.example.reshugoel.swachhbharat;

/**
 * Created by Reshu Goel on 6/18/2018.
 */

public class ComplaintSubmit {
    private String location, image;

    public ComplaintSubmit() {
    }

    public ComplaintSubmit(String location, String image) {
        this.location = location;
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
