package com.example.appbook.domain;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//una libreria tiene N libros y 1 libro se puede estar en N librerias. Relacion N a N.
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(foreignKeys = {
        @ForeignKey(entity = Book.class, parentColumns = "id", childColumns = "idBook", onDelete = CASCADE),
        @ForeignKey(entity = Library.class, parentColumns = "id", childColumns = "idLibrary", onDelete = CASCADE)
})
public class Stock {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo //(name = "id_book")
    private long idBook;
    @ColumnInfo//(name = "id_library")
    private long idLibrary;
    @ColumnInfo
    private int quantity;

    public Stock() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdBook() {
        return idBook;
    }

    public void setIdBook(long idBook) {
        this.idBook = idBook;
    }

    public long getIdLibrary() {
        return idLibrary;
    }

    public void setIdLibrary(long idLibrary) {
        this.idLibrary = idLibrary;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
