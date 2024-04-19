package com.example.hw1_todolist

// The API was updated to use Java.Time, older versions of android will break


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.ViewModelProvider
import com.example.hw1_todolist.R
// I couldn't get non-extended to show text next to the plus sign
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import androidx.lifecycle.Observer


// MainActivity: The heart of your application's UI.
// This class should coordinate the main user interactions and screen transitions.
class MainActivity : AppCompatActivity(), TaskItemClickListener {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskItemAdapter: TaskItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //View Models are used to store and manage UI-related data in a lifecycle conscious way
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        // The "Observer" allows the live data to be displayed from the RecyclerView. I couldn't get it to work without it
        taskViewModel.taskItems.observe(this, Observer { tasks ->
            taskItemAdapter.updateTasks(tasks)
        })

        val recyclerViewTasks = findViewById<RecyclerView>(R.id.rvTasks)
        taskItemAdapter = TaskItemAdapter(emptyList(), this)
        recyclerViewTasks.adapter = taskItemAdapter
        recyclerViewTasks.layoutManager = LinearLayoutManager(this)



        val fabNewTask = findViewById<ExtendedFloatingActionButton>(R.id.fabNewTask)

        fabNewTask.setOnClickListener {
            // I had tried to create and Edit tasks here, and the app would crash due to a null reference
            // Now NewTaskSheet handles stuff after the New Task button is pressed
            NewTaskSheet(null).show(supportFragmentManager, "newTaskTag")
        }
    }


    // These should be used to edit tasks from the RecyclerView/non-fragment screen, but it didn't work
    // I think the items in task_item.xml should be EditTexts and not ImageViews
    override fun editTaskItem(taskItem: TaskItem) {
        NewTaskSheet(taskItem).show(supportFragmentManager, "newTaskTag")
    }
    //Again, this could cross out once the done (circle icon) is pressed, but it doesn't
    override fun completeTaskItem(taskItem: TaskItem) {
        taskViewModel.setCompleted(taskItem.id)
    }

    override fun deleteTaskItem(taskItem: TaskItem) {
        taskViewModel.deleteTaskItem(taskItem.id)
    }
}



