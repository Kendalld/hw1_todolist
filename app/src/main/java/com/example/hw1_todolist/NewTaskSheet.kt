package com.example.hw1_todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

// NewTaskSheet: This Activity/Fragment is responsible for adding new tasks.
// Neglecting the input handling here will break the core functionality of task addition.
class NewTaskSheet : AppCompatActivity() {
    private var dueTime: LocalDateTime? = null
    // onCreate: Again, crucial for setting up the layout and functionality.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_new_task_sheet)

        // Set up the UI components like EditText and Button, and handle their events.


//private fun saveTask()
//{
//    val name = binding.name.text.toString()
//    val desc = binding.desc.text.toString()
//    if(taskItem == null)
//    {
//        val newTask = TaskItem(name,desc,null,null)
//        taskViewModel.addTaskItems(newTask)
//    }
//    else
//    {
//        taskViewModel.updateTaskItem(taskItem!!.id, name, desc, null)
//    }


    // TODO: Initialize your EditText and Button for task input and saving
    }
}
