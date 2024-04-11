package com.example.hw1_todolist


// TaskItemClickListener: An interface for handling clicks on items in your list.
// ESSENTIAL: You must implement this interface in your ViewHolder or Activity to respond to user interactions.
interface TaskItemClickListener {
    fun onItemClick(taskId: Int)

    fun onItemEdit(taskItem: TaskItem)
        // Open back the same functionality of add new task, but with the fields already filled in
    fun onItemDelete(taskItem: TaskItem){
        // remove from database and recycler view
    }


    // Add more methods as needed, like onItemDelete, onItemEdit, etc.
}
