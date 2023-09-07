package com.example.todolist.Details;

import com.example.todolist.Main.MainActivity;
import com.example.todolist.Models.DataDao;
import com.example.todolist.Models.Data_task;

public class DetailsPresenter implements DetailsContract.Presenter{

    DataDao dao;
    private Data_task data_task;
    DetailsContract.View view;

    public DetailsPresenter(DataDao dao, Data_task data_task,   DetailsContract.View view) {
        this.dao = dao;
        this.data_task = data_task;
        this.view=view;
    }

    @Override
    public void onAttach(DetailsContract.View view) {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void deleteData(Data_task data_task) {

    }

    @Override
    public void saveChanges(int importance, String title) {
        if(title.isEmpty()){
            view.showError("Task box is empty");
            return;
        }
        else {
            Data_task task = new Data_task();
            task.setIs_selected(false);
            task.setTask_title(title);
            task.setImportance(importance);
            long result = dao.addTask(task);
            task.setId(result);
            view.returnResult(MainActivity.RESULT_CODE_ADD, task);
        }

//       else {
//            data_task.setTask_title(title);
//            data_task.setImportance(importance);
//            int result= dao.updateTask(data_task);
//            if(result > 0 )
//                view.returnResult(MainActivity.RESULT_CODE_UPDATE,data_task);
//        }
    }
}
