package com.example.todolist.Details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.todolist.Models.Data_task;
import com.example.todolist.R;

public class DetailsActivity extends AppCompatActivity implements DetailsContract.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

    @Override
    public void btnDeleteVisibility(boolean visible) {

    }

    @Override
    public void showData(Data_task data_task) {

    }

    @Override
    public void showError(String message) {

    }
}