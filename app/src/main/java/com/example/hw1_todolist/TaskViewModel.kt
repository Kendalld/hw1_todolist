package com.example.hw1_todolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import java.time.LocalDate
import java.time.LocalTime
import com.example.hw1_todolist.TaskItem



// TaskViewModel: Handles the business logic of your task data, separating concerns from the UI.
// VITAL: This ViewModel should be the sole source of truth for your UI regarding task data.
class TaskViewModel : ViewModel() {
    // This mutable list holds the current list of TaskItems
    private val _taskItems = MutableLiveData<List<TaskItem>>(listOf()) // _ is convention for private mutable variables in Kotlin
    val taskItems: LiveData<List<TaskItem>> = _taskItems // Expose the list as LiveData to UI so it's non mutable



    // Adds TaskItem
    fun addTaskItem(name: String, desc: String, dueTime: LocalTime?, completedDate: LocalDate?) {
        val newTask = TaskItem(name, desc, dueTime, completedDate, nextTaskId())
        val updatedList = _taskItems.value.orEmpty() + newTask
        _taskItems.value = updatedList
    }

    // Updates a task item by its ID with new details
    fun updateTaskItem(id: Int, name: String, desc: String, dueTime: LocalTime?) {
        _taskItems.value = _taskItems.value?.map {// map used to iterate through the list of task items, and return a new list with the updated task item
            if (it.id == id) it.copy(name = name, desc = desc, dueTime = dueTime) else it // it is used in lambdas to access elements in list (here it's the TaskItem)
        }
    }

    // Marks a task item as completed by setting its completion date
    fun setCompleted(id: Int) {
        _taskItems.value = _taskItems.value?.map {
            if (it.id == id && it.completedDate == null) it.copy(completedDate = LocalDate.now()) else it
        }
    }

    // Deletes a task item by its ID
    fun deleteTaskItem(id: Int) {
        _taskItems.value = _taskItems.value?.filterNot { it.id == id }// filterNot used to iterate through the list of task items, and return a new list with the task item deleted (filtered out)
    }

    // TODO: check functionality
    var taskId = 1  // Simple counter to assign unique IDs to tasks
    fun nextTaskId(): Int {
        return taskId++
    }
}
