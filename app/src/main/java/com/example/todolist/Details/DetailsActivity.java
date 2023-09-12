package com.example.todolist.Details;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.todolist.Main.MainActivity;
import com.example.todolist.Main.Tasks_adapter;
import com.example.todolist.Models.Data_task;
import com.example.todolist.Models.Database_holder;
import com.example.todolist.R;
import com.google.android.material.snackbar.Snackbar;

public class DetailsActivity extends AppCompatActivity implements DetailsContract.View {

    private int importance=Data_task.IMPORTANCE_NORMAL;
    View btn_save;
   public TextView title;
    EditText et_title;

    DetailsContract.Presenter presenter;
    ImageView lastImportance;
    View btn_normal,btn_low,btn_high;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        btn_save = findViewById(R.id.btn_save_change);
        btn_normal = findViewById(R.id.btn_normal);
        btn_low = findViewById(R.id.btn_low);
        btn_high = findViewById(R.id.btn_high);
        et_title = findViewById(R.id.edit_title);
        title = findViewById(R.id.title);


        //these two lines are when update

        Data_task data;
        data = getIntent().getParcelableExtra(MainActivity.REQUEST_KEY);
        if(data != null) {
            title.setText(data.getTask_title());
            importance = data.getImportance();
        }


        presenter=new DetailsPresenter(Database_holder.getDatabase(this).getDataDao(),data,this);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //implement Snackbar for duplicate task


                //different status of save button
                if(title.getText() == "") {
                    presenter.saveChanges(importance, et_title.getText().toString(),v);
                }
                else if(title.getText() != "" && et_title.getText().toString().equals("")) {
                    presenter.saveChanges(importance, title.getText().toString(),v);
                }
                else if(title.getText() != "" && !et_title.getText().toString().equals("")) {
                    presenter.saveChanges(importance, et_title.getText().toString(),v);
                }


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
        intent.putExtra("selected",data_task.is_selected());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        setResult(resultCode,intent);
        finish();
    }

    @Override
    public String checkTitle() {
        return title.toString();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}