package com.example.todolist.Main;

import com.example.todolist.BasePeresenter;
import com.example.todolist.Models.Data_task;

import java.util.List;

public interface MainContract {

    interface View{
        void showData(List<Data_task> data_task);
        void addData(Data_task data_task);
        void removeData(Data_task data_task);
        void showEmptyTask(boolean visible);
        void updateData(Data_task data_task);
        void Delete();
    }

    interface Presenter extends BasePeresenter<View> {
        void search(String q);
        void onItemClick(Data_task data_task);
        void deleteAll();
    }
}
