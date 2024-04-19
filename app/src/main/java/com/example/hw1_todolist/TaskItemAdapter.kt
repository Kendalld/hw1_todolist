package com.example.hw1_todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hw1_todolist.R.layout


// TaskItemAdapter: Manages how task items are displayed and interacted with in a RecyclerView.
// CRITICAL: This adapter links your TaskItem data to the RecyclerView in the UI.
class TaskItemAdapter(
    private var taskList: List<TaskItem>,
    private val onItemClicked: TaskItemClickListener
) : RecyclerView.Adapter<TaskItemAdapter.TaskItemViewHolder>() {

    // This should be a separate file, but it stopped breaking when I put it here...
    class TaskItemViewHolder(itemView: View, private val listener: TaskItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val tvTaskTitle: TextView = itemView.findViewById(R.id.etTaskTitle)
        val tvTaskDesc: TextView = itemView.findViewById(R.id.etTaskDesc)
        val ivTaskAction: ImageView = itemView.findViewById(R.id.ivTaskAction)
        val ivDeleteTask: ImageView = itemView.findViewById(R.id.ivDeleteTask)
        val tvDueTime: TextView = itemView.findViewById(R.id.tvDueTime)

        fun bind(task: TaskItem) {
            tvTaskTitle.text = task.name
            tvTaskDesc.text = task.desc
            //tvDueTime.text = task.dueTime.toString() // returns null...

            ivTaskAction.setOnClickListener {
                listener.completeTaskItem(task)
            }

            ivDeleteTask.setOnClickListener {
                listener.deleteTaskItem(task)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        // Layout Inflater makes the other layouts accessible
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.task_item_cell, parent, false)
        return TaskItemViewHolder(itemView, onItemClicked)
    }

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        val currentTask = taskList[position]
        holder.bind(currentTask) // This is needed to bind the data to the ViewHolder
    }

    override fun getItemCount() = taskList.size

    fun updateTasks(tasks: List<TaskItem>) {
        this.taskList = tasks
       notifyDataSetChanged()// Without this the delete function won't remove the item from the RecyclerView
    }
}
