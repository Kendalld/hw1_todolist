package com.example.hw1_todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData


// TaskViewModel: Handles the business logic of your task data, separating concerns from the UI.
// VITAL: This ViewModel should be the sole source of truth for your UI regarding task data.
class TaskViewModel : ViewModel() {
    var taskItems= MutableLiveData<MutableList<TaskItem>>()

    fun init() {
        val taskItems.value = mutableListOf()
    }

    fun addTask(newTask: TaskItem) {
        val list = taskItems.value
        list.add(newTask)
        taskItems.postValue(list)
    }



// TODO: Implement the logic for managing task data, such as adding, deleting, and updating tasks.
}
