package com.pgmanager.entity;

public class RoomRequest {
    private String sharingType;
    private int bedCount;

    // Getters and setters
    public String getSharingType() {
        return sharingType;
    }

    public void setSharingType(String sharingType) {
        this.sharingType = sharingType;
    }

    public int getBedCount() {
        return bedCount;
    }

    public void setBedCount(int bedCount) {
        this.bedCount = bedCount;
    }
}
