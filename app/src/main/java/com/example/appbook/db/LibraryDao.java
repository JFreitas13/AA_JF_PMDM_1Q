package com.example.appbook.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.appbook.domain.Library;

import java.util.List;

@Dao
public interface LibraryDao {

    //buscar todos las librerias
    @Query("SELECT * FROM library")
    List<Library> getAll();

    //buscar por nombre
    @Query("SELECT * FROM library WHERE name = :name")
    Library getByName(String name);

    //buscar por id
    @Query("SELECT * FROM library WHERE libraryId = :id")
    Library getByLibraryId(long id);

    //borrar por id
    @Query("DELETE FROM library WHERE libraryId = :id")
    void deleteById(long id);

    //si quiero inserir uno o varios debo escribir void insert(Library... Library)
    @Insert
    void insert(Library library);

    //borrar
    @Delete
    void delete(Library library);

    //actualizar
    @Update
    void update(Library library);



}
