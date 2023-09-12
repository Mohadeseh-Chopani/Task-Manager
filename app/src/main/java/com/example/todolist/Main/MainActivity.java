package com.example.todolist.Main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.todolist.Models.Data_task;
import com.example.todolist.Models.Database_holder;
import com.example.todolist.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements Tasks_adapter.eventListener_main , MainContract.View{

    public static final int REQUEST_CODE=1000;
    public static final String REQUEST_KEY="data";
    public static final int RESULT_CODE_ADD=1001;
    public static final int RESULT_CODE_DELETE=1002;
    public static final int RESULT_CODE_UPDATE=1000;
    RecyclerView recyclerView;
    View btn_add;
    Tasks_adapter adapter;
    MainContract.Presenter presenter;

    ImageView empty_state_img;
    TextView empty_state_text;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        empty_state_img = findViewById(R.id.empty_state_img);
        empty_state_text = findViewById(R.id.empty_state_text);

        presenter=new MainPresenter(Database_holder.getDatabase(this).getDataDao(), this);

        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        adapter=new Tasks_adapter(this);
        recyclerView.setAdapter(adapter);



        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this,com.example.todolist.Details.DetailsActivity.class),REQUEST_CODE);
            }
        });


        presenter.onAttach(this);
    }

    @Override
    public void showData(List<Data_task> data_task) {
        adapter.add_tasks(data_task);
    }

    @Override
    public void addData(Data_task data_task) {

    }

    @Override
    public void removeData(Data_task data_task) {

    }

    @Override
    public void showEmptyTask(boolean visible) {

        if(!visible){
            empty_state_img.setVisibility(View.GONE);
            empty_state_text.setVisibility(View.GONE);
        }

    }

    @Override
    public void updateData(Data_task data_task) {

        adapter.update(data_task);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_CODE_ADD || resultCode == RESULT_CODE_UPDATE){
                Data_task data_task = new Data_task();
                data_task.setIs_selected(false);
                assert data != null;
                data_task.setTask_title(data.getStringExtra("title"));
                data_task.setImportance(data.getIntExtra("importance",0));
                data_task.setId(data.getIntExtra("id",1));
                data_task.setIs_selected(data.getBooleanExtra("selected",false));


                if(resultCode == RESULT_CODE_ADD) {
                    adapter.add(data_task);
                    recyclerView.smoothScrollToPosition(0);
                }
                else if(resultCode == RESULT_CODE_UPDATE){
                    adapter.update(data_task);
                }

                showEmptyTask(adapter.getItemCount() == 0);
            }
        }
    }

    @Override
    public void onClick(Data_task data_task) {
        presenter.onItemClick(data_task);
    }


    // this method is called when the name task is duplicate

    @Override
    public void onLongClick(Data_task data_task) {
        Intent intent= new Intent(MainActivity.this,com.example.todolist.Details.DetailsActivity.class);
        intent.putExtra(REQUEST_KEY,data_task);
        startActivityForResult(intent ,REQUEST_CODE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}