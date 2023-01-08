package com.example.appbook.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.NonNull;


//@Entity(foreignKeys = {
//        @ForeignKey(entity = Publisher.class, parentColumns = "id", childColumns = "idPublisher", onDelete = CASCADE)
//})
@Entity
public class Book {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo
    @NonNull
    private String name;
    @ColumnInfo
    private int yearEdition;
    @ColumnInfo
    private int pagesNumber;
    @ColumnInfo
    private String description;
    @ColumnInfo
    private boolean read;
    @ColumnInfo //(name = "id_publisher")
    private long idPublisher;

    public Book(long id, String name, int yearEdition, int pagesNumber, String descripcion /*, boolean ebook*/) {
        this.id = id;
        this.name = name;
        this.yearEdition = yearEdition;
        this.pagesNumber = pagesNumber;
        this.description = descripcion;
        /*this.ebook = ebook;*/
    }

    public Book(String name, int yearEdition, int pagesNumber, String descripcion /*, boolean ebook*/) {
        this.name = name;
        this.yearEdition = yearEdition;
        this.pagesNumber = pagesNumber;
        this.description = descripcion;
        /*this.ebook = ebook;*/
    }

    public Book() {
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

    public int getYearEdition() {
        return yearEdition;
    }

    public String getYearEditionString() {

        String yearEditionString = String.valueOf(yearEdition);
        return yearEditionString;
    }

    public void setYearEdition(int yearEdition) {
        this.yearEdition = yearEdition;
    }

    public int getPagesNumber() {
        return pagesNumber;
    }

    public String getPagesNumberString() {
        String pagesNumberString = String.valueOf(pagesNumber);
        return pagesNumberString;
    }

    public void setPagesNumber(int pagesNumber) {
        this.pagesNumber = pagesNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public long getIdPublisher() {
        return idPublisher;
    }

    public void setIdPublisher(long idPublisher) {
        this.idPublisher = idPublisher;
    }
}
