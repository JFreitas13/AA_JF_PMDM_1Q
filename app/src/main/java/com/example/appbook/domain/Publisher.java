package com.example.appbook.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Publisher {

    @PrimaryKey (autoGenerate = true)
    private long publisherId;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String phoneNumber;
    @ColumnInfo
    private String description;

    public Publisher() {
    }

    public Publisher(String name, String phoneNumber, String description) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.description = description;
    }

    public Publisher(long publisherId, String name, String phoneNumber, String description) {
        this.publisherId = publisherId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.description = description;
    }

    public long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(long publisherId) {
        this.publisherId = publisherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
