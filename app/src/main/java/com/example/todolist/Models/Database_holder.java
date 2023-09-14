package com.example.todolist.Models;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

@Database(version = 1,exportSchema = false,entities = {Data_task.class})
public abstract class Database_holder extends RoomDatabase {

    public static final String DATABASE_NAME = "db";
    private static Database_holder database_holder;

    public static Database_holder getDatabase(Context context){
        if(database_holder==null){
            database_holder= Room.databaseBuilder(context.getApplicationContext(),Database_holder.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return database_holder;
    }

    public abstract DataDao getDataDao();
}
