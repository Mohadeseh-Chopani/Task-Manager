package com.example.todolist;

public interface BasePeresenter<T>{
    void onAttach(T view);
    void onDetach();
}
