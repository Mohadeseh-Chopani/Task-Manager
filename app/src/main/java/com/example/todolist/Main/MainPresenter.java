package com.example.todolist.Main;

import com.example.todolist.Models.DataDao;
import com.example.todolist.Models.Data_task;

import java.util.List;

public class MainPresenter implements MainContract.Presenter{

    MainContract.View view ;
    List<Data_task>data;
    DataDao dao;

    public MainPresenter( DataDao dao , MainContract.View view ){
        this.dao = dao;
        this.data=dao.getTaskList();
        this.view=view;
    }

    @Override
    public void search(String q) {

    }

    @Override
    public void onItemClick(Data_task data_task) {
        data_task.setIs_selected(!data_task.is_selected());
        int result=dao.updateTask(data_task);
        if(result > 0)
            view.updateData(data_task);
    }

    @Override
    public void deleteAll() {
        int result=dao.deleteAll();
        if(result > 0)
            view.Delete();
    }

    @Override
    public void onAttach(MainContract.View view) {
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
