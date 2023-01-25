package com.example.appbook.domain;

import static androidx.room.ForeignKey.CASCADE;
import static androidx.room.ForeignKey.SET_NULL;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "books",
        foreignKeys = {
            @ForeignKey(entity = Library.class, parentColumns = "libraryId", childColumns = "library_id", onDelete = SET_NULL),
            @ForeignKey(entity = Publisher.class, parentColumns = "publisherId", childColumns = "publisher_id", onDelete = SET_NULL)
        })
public class Book {

    @PrimaryKey(autoGenerate = true)
    private long bookId;
    @ColumnInfo
    public long library_id; //Id de la libreria
    @ColumnInfo
    public long publisher_id; //id de la editorial
    @ColumnInfo
    private String name;
    @ColumnInfo
    private int yearEdition;
    @ColumnInfo
    private int pagesNumber;
    @ColumnInfo
    private String description;
    @ColumnInfo
    private boolean read;

    public Book(long bookId, long library_id, long publisher_id, String name, int yearEdition, int pagesNumber, String description, boolean read) {
        this.bookId = bookId;
        this.library_id = library_id;
        this.publisher_id = publisher_id;
        this.name = name;
        this.yearEdition = yearEdition;
        this.pagesNumber = pagesNumber;
        this.description = description;
        this.read = read;
    }

    public Book(String name, int yearEdition, int pagesNumber, String description) {
        this.name = name;
        this.yearEdition = yearEdition;
        this.pagesNumber = pagesNumber;
        this.description = description;
    }

    public Book() {
    }

    public Book( long library_id, long publisher_id, String name, int yearEdition, int pagesNumber, String description, boolean read) {
        this.name = name;
        this.yearEdition = yearEdition;
        this.pagesNumber = pagesNumber;
        this.description = description;
        this.library_id = library_id;
        this.publisher_id = publisher_id;
        this.read = read;
    }

    public Book(String name, int yearEdition, int pagesNumber, String description, boolean read) {
        this.name = name;
        this.yearEdition = yearEdition;
        this.pagesNumber = pagesNumber;
        this.description = description;
        this.read = read;

    }

    public long getLibrary_id() {
        return library_id;
    }

    public void setLibrary_id(long library_id) {
        this.library_id = library_id;
    }

    public long getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(long publisher_id) {
        this.publisher_id = publisher_id;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
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

}
