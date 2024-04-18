package com.example.hw1_todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hw1_todolist.R.layout


// TaskItemAdapter: Manages how task items are displayed and interacted with in a RecyclerView.
// CRITICAL: This adapter links your TaskItem data to the RecyclerView in the UI.
class TaskItemAdapter(
    private var taskList: List<TaskItem>,
    private val onItemClicked: TaskItemClickListener
) : RecyclerView.Adapter<TaskItemAdapter.TaskItemViewHolder>() {

    class TaskItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskNameTextView: TextView = itemView.findViewById(R.id.etName)
        val taskDescTextView: TextView = itemView.findViewById(R.id.etDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_new_task_sheet, parent, false)
        return TaskItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        val currentTask = taskList[position]
        holder.taskNameTextView.text = currentTask.name
        holder.taskDescTextView.text = currentTask.desc
        holder.itemView.setOnClickListener { onItemClicked.editTaskItem(currentTask) }
    }

    override fun getItemCount() = taskList.size

    fun updateTasks(tasks: List<TaskItem>) {
        this.taskList = tasks
        notifyDataSetChanged()
    }
}
