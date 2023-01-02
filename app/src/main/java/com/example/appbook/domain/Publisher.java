package com.example.appbook.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Publisher {

    @PrimaryKey (autoGenerate = true)
    private long id;
    @ColumnInfo
    @NonNull
    private String name;
    @ColumnInfo
    private String city;
    @ColumnInfo //(name = "zip_code")
    private String zipCode;
    @ColumnInfo //(name = "phone_number")
    private String phoneNumber;
    @ColumnInfo
    private String descripriton;

    public Publisher() {
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

    public String getDescripriton() {
        return descripriton;
    }

    public void setDescripriton(String descripriton) {
        this.descripriton = descripriton;
    }
}
