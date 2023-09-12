package com.example.todolist.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName ="db")
public class Data_task implements Parcelable {

    public Data_task(){

    }

    @PrimaryKey(autoGenerate = true)
    private long id = 0;

    public long getId() {
        return id;
    }

    public void setId(long id) {this.id=id;}

    public String getTask_title() {
        return task_title;
    }

    public void setTask_title(String task_title) {
        this.task_title = task_title;
    }

    public boolean is_selected() {
        return is_selected;
    }

    public void setIs_selected(boolean is_selected) {
        this.is_selected = is_selected;
    }

    private String task_title;
    private boolean is_selected=false;


    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    private int importance=IMPORTANCE_NORMAL;

    public static final int IMPORTANCE_HIGH = 2;
    public static final int IMPORTANCE_NORMAL = 0;
    public static final int IMPORTANCE_LOW = 1;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.task_title);
        dest.writeByte(this.is_selected ? (byte) 1 : (byte) 0);
        dest.writeInt(this.importance);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readLong();
        this.task_title = source.readString();
        this.is_selected = source.readByte() != 0;
        this.importance = source.readInt();
    }

    protected Data_task(Parcel in) {
        this.id = in.readLong();
        this.task_title = in.readString();
        this.is_selected = in.readByte() != 0;
        this.importance = in.readInt();
    }

    public static final Parcelable.Creator<Data_task> CREATOR = new Parcelable.Creator<Data_task>() {
        @Override
        public Data_task createFromParcel(Parcel source) {
            return new Data_task(source);
        }

        @Override
        public Data_task[] newArray(int size) {
            return new Data_task[size];
        }
    };
}
