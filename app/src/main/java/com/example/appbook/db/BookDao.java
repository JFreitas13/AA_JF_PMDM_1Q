package com.example.appbook.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.appbook.domain.Book;

import java.util.List;

@Dao
public interface BookDao {

    //buscar todos los libros
    @Query("SELECT * FROM book")
    List<Book> getAll();

    //buscar por nombre
    @Query("SELECT * FROM book WHERE name = :name")
    Book getByName(String name);

    //borrar por id
    @Query("DELETE FROM book WHERE id = :id")
    void deleteById(long id);

    //
    @Insert
    void insert(Book book);

    @Delete
    void delete(Book book);

    @Update
    void update(Book book);
}
