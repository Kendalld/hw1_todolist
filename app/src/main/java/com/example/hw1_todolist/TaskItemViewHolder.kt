//package com.example.hw1_todolist
//
//import android.view.View
//import androidx.recyclerview.widget.RecyclerView
//import android.widget.TextView
//import android.widget.ImageView
//import com.example.hw1_todolist.R
//
//// This is represented in TaskItemAdapter.kt, it should be redundant code that can be deleted
//// I've broken this app too many times so I'm not going to tempt fate...
//
//
//// TaskItemViewHolder: Holds the view for each task item in the list.
//// VERY IMPORTANT: This class binds individual views in the RecyclerView to your data.
//class TaskItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//    val tvTaskTitle: TextView = itemView.findViewById(R.id.etTaskTitle)
//    val tvTaskDesc: TextView = itemView.findViewById(R.id.etTaskDesc)
//    val ivTaskAction: ImageView = itemView.findViewById(R.id.ivTaskAction)
//    val ivDeleteTask: ImageView = itemView.findViewById(R.id.ivDeleteTask)
//
//
//
//    fun bind(task: TaskItem, listener: TaskItemClickListener) {
//        tvTaskTitle.text = task.name
//        tvTaskDesc.text = task.desc
//
//        ivTaskAction.setOnClickListener {
//            listener.completeTaskItem(task)
//        }
//
//        ivDeleteTask.setOnClickListener {
//            listener.deleteTaskItem(task)
//        }
//    }
//}