package com.example.todolist.Main;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.Models.Data_task;
import com.example.todolist.R;

import java.util.ArrayList;
import java.util.List;

public class Tasks_adapter extends RecyclerView.Adapter<Tasks_adapter.Tasks_viewHolder> {

    List<Data_task> Tasks = new ArrayList<>();

    EventListener eventListener;

    public Tasks_adapter(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void add(Data_task task) {
        Tasks.add(0, task);
        notifyItemInserted(0);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void add_tasks(List<Data_task> tasks) {
        this.Tasks.addAll(tasks);
        notifyDataSetChanged();
    }

    public void deleteAll(){
       Tasks.clear();
       notifyDataSetChanged();
    }

    public void update(Data_task task) {
        for (int i = 0; i < Tasks.size(); i++) {
            if (Tasks.get(i).getId() == task.getId()) {
                Tasks.set(i, task);
                notifyItemChanged(i);
            }
        }
    }

    @NonNull
    @Override
    public Tasks_viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new Tasks_viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Tasks_viewHolder holder, int position) {
        if (Tasks != null)
            holder.getTask(Tasks.get(position));
    }

    @Override
    public int getItemCount() {
        return Tasks.size();
    }

    public class Tasks_viewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView checked_item;
        View importance;


        public Tasks_viewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            checked_item = itemView.findViewById(R.id.checkedItem);
            importance = itemView.findViewById(R.id.importanceView);

        }

        public void getTask(Data_task data_task) {
            title.setText(data_task.getTask_title());
            if (data_task.is_selected()) {
                checked_item.setBackgroundResource(R.drawable.shape_item_checked);
                checked_item.setImageResource(R.drawable.baseline_done_24);
                title.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                checked_item.setImageResource(0);
                checked_item.setBackgroundResource(R.drawable.shape_checkbox_default);
                title.setPaintFlags(0);
            }

            if (data_task.getImportance() == Data_task.IMPORTANCE_HIGH)
                importance.setBackgroundResource(R.drawable.shape_high);
            else if (data_task.getImportance() == Data_task.IMPORTANCE_LOW) {
                importance.setBackgroundResource(R.drawable.shape_low);
            } else
                importance.setBackgroundResource(R.drawable.shape_normal);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eventListener.onClick(data_task);
                }
            });
        }
    }

   public interface EventListener {
        void onClick(Data_task data_task);
    }
}
