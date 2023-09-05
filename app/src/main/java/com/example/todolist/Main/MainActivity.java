package com.example.todolist.Main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.todolist.DialogAdd;
import com.example.todolist.DialogEdit;
import com.example.todolist.Main.Tasks_adapter;
import com.example.todolist.Models.DataDao;
import com.example.todolist.Models.Data_task;
import com.example.todolist.Models.Database_holder;
import com.example.todolist.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View{



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void showData(Data_task data_task) {

    }

    @Override
    public void addData(Data_task data_task) {

    }

    @Override
    public void removeData(Data_task data_task) {

    }

    @Override
    public void showEmptyTask(boolean visible) {

    }

    @Override
    public void updateData(Data_task data_task) {

    }
}