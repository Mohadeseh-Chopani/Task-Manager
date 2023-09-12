package com.example.todolist.Models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataDao {

    @Insert
    long addTask(Data_task data_task);

    @Query("SELECT * FROM db")
    List<Data_task>getTaskList();

    @Update
    int updateTask(Data_task data_task);

    @Delete
    int deleteTask(Data_task data_task);

    @Query("SELECT * FROM db WHERE task_title == :title AND importance == :status")
    boolean checkExist(String title, int status);

    @Query("SELECT * FROM db WHERE task_title like '%' || :query || '%'")
    List<Data_task>searchTask(String query);
}
