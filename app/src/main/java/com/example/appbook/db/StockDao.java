package com.example.appbook.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.appbook.domain.Stock;

import java.util.List;

@Dao
public interface StockDao {

    //buscar todos
    @Query("SELECT * FROM stock")
    List<Stock> getAll();

    //buscar por id
    @Query("SELECT * FROM stock WHERE id = :id")
    Stock getByID(long id);

    //todas las librerias que tienen stock de un libro
    @Query("SELECT * FROM stock WHERE idBook = :idBook")
    List<Stock> getStockByIdBook(long idBook);

    //todos los libros con stock en una libreria
    @Query("SELECT * FROM stock WHERE idLibrary = :idLibrary")
    List<Stock> getStockByIdLibrary(long idLibrary);

    @Insert
    void insert(Stock stock);

    @Delete
    void delete(Stock stock);

    @Update
    void update(Stock stock);



}
