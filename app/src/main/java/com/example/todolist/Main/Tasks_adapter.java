package com.example.todolist.Main;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.Models.Data_task;
import com.example.todolist.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tasks_adapter extends RecyclerView.Adapter<Tasks_adapter.Tasks_viewHolder> {

    List<Data_task> Tasks = new ArrayList<>();

    eventListener_main eventListener_main;

    public Tasks_adapter(eventListener_main eventListener) {
        this.eventListener_main = eventListener;
    }

    public void add(Data_task task) {
        Tasks.add(0, task);
        notifyItemInserted(0);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void add_tasks(List<Data_task> tasks) {
        this.Tasks = tasks;
        notifyDataSetChanged();
    }

    public void update(Data_task task) {
        for (int i = 0; i < Tasks.size(); i++) {
            if (task.getId() == Tasks.get(i).getId()) {
                Tasks.set(i, task);
                notifyItemChanged(i);
            }
        }
    }

    public void delete(Data_task task) {
        for (int i = 0; i < Tasks.size(); i++) {
            if (task.getId() == Tasks.get(i).getId()) {
                Tasks.remove(i);
                notifyItemRemoved(i);
                break;
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
//        if (Tasks != null)
        holder.getTask(Tasks.get(position));
    }

    @Override
    public int getItemCount() {
        return Tasks.size();
    }

    public class Tasks_viewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView checked_item,btn_delete;
        View importance;


        public Tasks_viewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            checked_item = itemView.findViewById(R.id.checkedItem);
            importance = itemView.findViewById(R.id.importanceView);
            btn_delete=itemView.findViewById(R.id.btn_delete);

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
                    eventListener_main.onClick(data_task);
                }
            });


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    eventListener_main.onLongClick(data_task);
                    return false;
                }
            });

            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eventListener_main.onDeleteClick(data_task);
                }
            });
        }
    }

   public interface eventListener_main {
        void onClick(Data_task data_task);
        void onLongClick(Data_task data_task);
        void onDeleteClick(Data_task data_task);
    }
}
