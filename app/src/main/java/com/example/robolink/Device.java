package com.example.robolink;

public class Device {

    private String name;
    private int imageID;
    private int imageID2;


    public Device(String name, int imageID, int imageID2) {
        this.name = name;
        this.imageID = imageID;
        this.imageID2 = imageID2;
    }

    public Device(String name, int imageID) {
        this.name = name;
        this.imageID = imageID;
    }

    public String getName() {
        return name;
    }

    public int getImageID() {
        return imageID;
    }

    public int getImageID2() {
        return imageID2;
    }
}
