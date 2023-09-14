package com.example.todolist.Details;

import android.view.View;

import com.example.todolist.Main.MainActivity;
import com.example.todolist.Models.DataDao;
import com.example.todolist.Models.Data_task;
import com.google.android.material.snackbar.Snackbar;

public class DetailsPresenter implements DetailsContract.Presenter{

    DataDao dao;
     Data_task data_task;
    DetailsContract.View view;

    public DetailsPresenter(DataDao dao, Data_task data_task) {
        this.dao = dao;
        this.data_task = data_task;
    }

    @Override
    public void onAttach(DetailsContract.View view) {

        this.view=view;
        if(data_task != null) {
            view.showData(data_task);
        }
    }

    @Override
    public void onDetach() {

    }

    @Override
    public void saveChanges(int importance, String title, View v) {
        if(title.equals("")){
            view.showError("Task box is empty");
            return;
        }
        if(data_task == null){
            Data_task task = new Data_task();
            task.setTask_title(title);
            task.setImportance(importance);
            if(!(dao.checkExist(task.getTask_title(),task.getImportance()))){
                long result = dao.addTask(task);
                task.setId(result);
                view.returnResult(MainActivity.RESULT_CODE_ADD, task);
            }
            else {
                Snackbar snackbar=Snackbar.make(v,"This task is already exists",Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });

                snackbar.show();
            }
        }
       else {
            data_task.setTask_title(title);
            data_task.setImportance(importance);
            if(!(dao.checkExist(data_task.getTask_title(),data_task.getImportance()))) {
                int result = dao.updateTask(data_task);
                if (result > 0)
                    view.returnResult(MainActivity.RESULT_CODE_UPDATE, data_task);
            }
            else {
                Snackbar snackbar=Snackbar.make(v,"This task is already exists",Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });

                snackbar.show();
            }
        }
    }
}
