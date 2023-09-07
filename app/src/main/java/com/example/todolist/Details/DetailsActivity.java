package com.example.todolist.Details;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.todolist.Main.MainActivity;
import com.example.todolist.Main.MainContract;
import com.example.todolist.Models.Data_task;
import com.example.todolist.Models.Database_holder;
import com.example.todolist.R;

public class DetailsActivity extends AppCompatActivity implements DetailsContract.View{

    private int importance=Data_task.IMPORTANCE_LOW;
    View btn_save;
    EditText et_title;

    DetailsContract.Presenter presenter;
    ImageView lastImportance;
    View btn_normal,btn_low,btn_high;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        presenter=new DetailsPresenter(Database_holder.getDatabase(this).getDataDao(),getIntent().getParcelableExtra(MainActivity.REQUEST_KEY), this);
        btn_save = findViewById(R.id.btn_save_change);
        btn_normal = findViewById(R.id.btn_normal);
        btn_low = findViewById(R.id.btn_low);
        btn_high = findViewById(R.id.btn_high);
        et_title = findViewById(R.id.edit_title);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.saveChanges(importance,et_title.getText().toString());
            }
        });

        lastImportance = btn_normal.findViewById(R.id.checked_normal);

        btn_high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(importance != Data_task.IMPORTANCE_HIGH){
                    lastImportance.setImageResource(0);
                    ImageView imageView=findViewById(R.id.checked_high);
                    imageView.setImageResource(R.drawable.baseline_done_24);
                    importance=Data_task.IMPORTANCE_HIGH;

                    lastImportance=imageView;
                }
            }
        });

        btn_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(importance != Data_task.IMPORTANCE_LOW){
                    lastImportance.setImageResource(0);
                    ImageView imageView=findViewById(R.id.checked_low);
                    imageView.setImageResource(R.drawable.baseline_done_24);
                    importance=Data_task.IMPORTANCE_LOW;

                    lastImportance=imageView;
                }
            }
        });

        btn_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(importance != Data_task.IMPORTANCE_NORMAL){
                    lastImportance.setImageResource(0);
                    ImageView imageView=findViewById(R.id.checked_normal);
                    imageView.setImageResource(R.drawable.baseline_done_24);
                    importance=Data_task.IMPORTANCE_NORMAL;

                    lastImportance=imageView;
                }
            }
        });


        presenter.onAttach(this);
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

    @Override
    public void returnResult(int resultCode, Data_task data_task) {
        Intent intent= new Intent();
        intent.putExtra("title",data_task.getTask_title());
        intent.putExtra("id",data_task.getId());
        intent.putExtra("importance",data_task.getImportance());
        setResult(resultCode,intent);
        finish();
    }
}