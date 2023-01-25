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
    @Query("SELECT * FROM books")
    List<Book> getAll();

    //buscar por nombre
    @Query("SELECT * FROM books WHERE name = :name")
    Book getByName(String name);

    //buscar por ID
    @Query("SELECT * FROM books WHERE bookId = :id")
    Book getById(long id);

    //borrar por id
    @Query("DELETE FROM books WHERE bookId = :id")
    void deleteById(long id);

    //si quiero inserir uno o varios debo escribir void insert(Book... book)
    @Insert
    void insert(Book book);

    //borrar
    @Delete
    void delete(Book book);

    //actualizar
    @Update
    void update(Book book);
}
