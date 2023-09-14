package com.example.todolist.Details;

import android.view.View;

import com.example.todolist.BasePeresenter;
import com.example.todolist.Models.Data_task;

public interface DetailsContract {

    interface View{
        void showData(Data_task data_task);
        void showError(String message);
        void returnResult(int resultCode,Data_task data_task);
    }

    interface Presenter extends BasePeresenter<View> {
        void saveChanges(int importance, String title, android.view.View view);
    }
}
