package com.example.todolist.Details;

import com.example.todolist.BasePeresenter;
import com.example.todolist.Models.Data_task;

public interface DetailsContract {

    interface View{
        void btnDeleteVisibility(boolean visible);
        void showData(Data_task data_task);
        void showError(String message);
    }

    interface Presenter extends BasePeresenter<View> {
        void deleteData(Data_task data_task);
        void saveChanges(Data_task data_task);
    }
}
