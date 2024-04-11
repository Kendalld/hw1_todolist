package com.example.hw1_todolist

import android.content.Context
//date
import android.view.View
import androidx.recyclerview.widget.RecyclerView

// TaskItemViewHolder: Holds the view for each task item in the list.
// VERY IMPORTANT: This class binds individual views in the RecyclerView to your data.
class TaskItemViewHolder(
    itemView: View,
    private val context: Context,
    private val taskItemClickListener: TaskItemClickListener
) : RecyclerView.ViewHolder(itemView) {

    // TODO: Initialize your task item views, like TextView for the title, CheckBox for status, etc.
    fun bind(task: Task) {
        itemView.name.text = task.name

        if(task.completed()){
            // TODO: Set the status to completed
        }

        itemView.completeButton.setOnClickListener {
            clickListener.onTaskItemClicked(task)
        }

        if(task.dueTime != null){
            itemView.dueTime.text = "Due: ${task.dueTime}"
        })
        else
            itemView.dueTime.text = "Due: None"
    }
}
