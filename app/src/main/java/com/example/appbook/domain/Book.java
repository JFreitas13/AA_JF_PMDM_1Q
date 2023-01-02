package com.example.appbook.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.Year;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @NonNull
    private String name;
    @ColumnInfo
    private int yearEdition;
    @ColumnInfo
    private int pagesNumber;
    @ColumnInfo
    private String descripcion;
    @ColumnInfo
    private boolean ebook;
}
