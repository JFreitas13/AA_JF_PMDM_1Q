package com.example.appbook.db;

import androidx.annotation.BoolRes;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.appbook.domain.Publisher;

import java.util.List;

@Dao
public interface PublisherDao {

    //buscar todas las editoriales
    @Query("SELECT * FROM publisher")
    List<Publisher> getAll();

    //buscar por nombre
    @Query("SELECT * FROM publisher WHERE name = :name")
    Publisher getByName(String name);

    //buscar por id
    @Query("SELECT * FROM publisher WHERE id = :id")
    Publisher getById(long id);

    //borrar por id
    @Query("DELETE FROM publisher WHERE id = :id")
    void deleteById(long id);

    //añadir
    @Insert
    void insert(Publisher publisher);

    //borrar
    @Delete
    void delete(Publisher publisher);

    //actualizar
    @Update
    void update(Publisher publisher);


}
