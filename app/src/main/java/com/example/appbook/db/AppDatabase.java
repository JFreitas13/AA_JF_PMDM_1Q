package com.example.appbook.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.appbook.domain.Book;
import com.example.appbook.domain.Library;
import com.example.appbook.domain.Publisher;
import com.example.appbook.domain.Stock;

@Database(entities= {Book.class, Library.class, Publisher.class, Stock.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract BookDao bookDao();
    public abstract LibraryDao libraryDao();
    public abstract PublisherDao publisherDao();
    public abstract StockDao stockDao();

}
