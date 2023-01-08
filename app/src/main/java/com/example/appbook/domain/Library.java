package com.example.appbook.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Library {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String city;
    @ColumnInfo //(name = "zip_code")
    private String zipCode;
    @ColumnInfo //(name = "phoneNumber")
    private String phoneNumber;
    @ColumnInfo
    private double latitude;
    @ColumnInfo
    private double longitude;

    public Library() {
    }

//    public Library(long id, String name, String city, String zipCode, String phoneNumber, double latitude, double longitude) {
//        this.id = id;
//        this.name = name;
//        this.city = city;
//        this.zipCode = zipCode;
//        this.phoneNumber = phoneNumber;
//        this.latitude = latitude;
//        this.longitude = longitude;
//    }

    public Library(long id, String name, String city, String zipCode, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
    }

    public Library(String name, String city, String zipCode, String phoneNumber) {
        this.name = name;
        this.city = city;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
    }

    public Library(String name, String city, String zipCode, String phoneNumber, double latitude, double longitud) {
        this.name = name;
        this.city = city;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.latitude = latitude;
        this.longitude = longitud;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
