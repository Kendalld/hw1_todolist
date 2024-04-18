package com.example.hw1_todolist

import android.annotation.SuppressLint
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView

import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer

import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.timepicker.MaterialTimePicker

import java.time.LocalTime

import com.example.hw1_todolist.*
import com.google.android.material.timepicker.TimeFormat as TimeFormat1



// MainActivity: The heart of your application's UI.
// This class should coordinate the main user interactions and screen transitions.
class MainActivity : AppCompatActivity(), TaskItemClickListener {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskItemAdapter: TaskItemAdapter


    // onCreate: Critical for initializing the activity and setting up the UI components.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        taskViewModel.taskItems.observe(this, Observer { taskItem ->
            taskItemAdapter.updateTasks(taskItem)
        })

        // RecyclerView setup: Essential for displaying a list of items.
        // You MUST properly initialize and configure your RecyclerView and its adapter.
        val recyclerViewTasks = findViewById<RecyclerView>(R.id.rvTasks)
        val fabNewTask = findViewById<FloatingActionButton>(R.id.fabNewTask)
        val editTextTaskName = findViewById<EditText>(R.id.etName)
        val editTextTaskDesc = findViewById<EditText>(R.id.etDesc)
        val ivDelete = findViewById<ImageView>(R.id.ivDeleteTask)


        taskItemAdapter = TaskItemAdapter(emptyList(), this)
        recyclerViewTasks.adapter = taskItemAdapter
        recyclerViewTasks.layoutManager = LinearLayoutManager(this)

        fabNewTask.setOnClickListener {
            val taskName = editTextTaskName.text.toString()
            val taskDesc = editTextTaskDesc.text.toString()

            if (taskName.isNotEmpty()) {
                showTimePicker { time ->
                    val taskItem = TaskItem(
                        name = taskName,
                        desc = taskDesc,
                        dueTime = time,
                        completedDate = null,
                        id = taskViewModel.nextTaskId()
                    )
                    taskViewModel.addTaskItem(taskItem.name, taskItem.desc, taskItem.dueTime, taskItem.completedDate) // explicitly called due to errors
                    editTextTaskName.text.clear()
                    editTextTaskDesc.text.clear()
                }
            }
        }
    }

    @SuppressLint("NewApi")
    private fun showTimePicker(onTimeSet: (LocalTime) -> Unit) {
        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat1.CLOCK_24H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Due Time")
            .build()

        picker.addOnPositiveButtonClickListener {
            onTimeSet(LocalTime.of(picker.hour, picker.minute))
        }

        picker.show(supportFragmentManager, "tag")
    }

    override fun editTaskItem(taskItem: TaskItem) {
        NewTaskSheet(taskItem).show(supportFragmentManager, "newTaskTag")
    }

    override fun completeTaskItem(taskItem: TaskItem) {
        taskViewModel.setCompleted(taskItem.id)
    }

    fun deleteTaskItem(taskItem: TaskItem) {
        taskViewModel.deleteTaskItem(taskItem.id)
    }

}



