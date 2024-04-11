package com.example.hw1_todolist

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

// TaskItemAdapter: Manages how task items are displayed and interacted with in a RecyclerView.
// CRITICAL: This adapter links your TaskItem data to the RecyclerView in the UI.
class TaskItemAdapter(
    private val taskItems: List<TaskItem>,
    private val onItemClicked: TaskItemClickListener
) : RecyclerView.Adapter<TaskItemViewHolder>() {

    // TODO: Implement necessary methods like onCreateViewHolder, onBindViewHolder, and getItemCount
    // onCreateViewHolder: Create new views (invoked by the layout manager)
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        val from = LayoutInflater.from(parent.context)

        return TaskItemViewHolder(from.inflate(R.layout.task_item, parent, false))
    }
    // onBindViewHolder: Replace the contents of a view (invoked by the layout manager)
    fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        TODO()
    }
    // getItemCount: Return the size of your dataset (invoked by the layout manager)
    fun getItemCount(): Int {
        TODO()
    }
}
