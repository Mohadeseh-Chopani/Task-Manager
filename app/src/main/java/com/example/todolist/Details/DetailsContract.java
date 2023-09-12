package com.example.todolist.Details;

import android.view.View;

import com.example.todolist.BasePeresenter;
import com.example.todolist.Models.Data_task;

public interface DetailsContract {

    interface View{
        void btnDeleteVisibility(boolean visible);
        void showData(Data_task data_task);
        void showError(String message);
        void returnResult(int resultCode,Data_task data_task);
        String checkTitle();
    }

    interface Presenter extends BasePeresenter<View> {
        void deleteData(Data_task data_task);
        void saveChanges(int importance, String title, android.view.View view);
    }
}
