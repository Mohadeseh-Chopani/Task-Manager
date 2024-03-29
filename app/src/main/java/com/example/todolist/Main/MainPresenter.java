package com.example.todolist.Main;

import com.example.todolist.Models.DataDao;
import com.example.todolist.Models.Data_task;
import com.example.todolist.Models.Database_holder;

import java.util.List;

public class MainPresenter implements MainContract.Presenter{

    MainContract.View view ;
    List<Data_task>data;
    DataDao dao;

    public MainPresenter( DataDao dao ){
        this.dao = dao;
        this.data=dao.getTaskList();
    }

    @Override
    public void search(String q) {
        if(q != null) {
            List<Data_task> tasks = dao.searchTask(q);
            view.showData(tasks);
        }else{
            List<Data_task> tasks = dao.getTaskList();
            view.showData(tasks);
        }
    }

    @Override
    public void onItemClick(Data_task data_task) {
        data_task.setIs_selected(!data_task.is_selected());
        int result=dao.updateTask(data_task);
        if(result > 0)
            view.updateData(data_task);
    }


    @Override
    public void onAttach(MainContract.View view) {
        this.view=view;
        if(data != null){
            view.showData(data);
            view.showEmptyTask(false);
        }else
            view.showEmptyTask(true);
    }

    @Override
    public void onDetach() {

    }
}
