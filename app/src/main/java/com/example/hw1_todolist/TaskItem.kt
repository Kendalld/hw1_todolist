package com.example.hw1_todolist

import java.time.LocalDate

// TaskItem: Represents the data structure for a task in your to-do list.
// IMPORTANT: This class must be well-defined; it's the blueprint for your task data.
data class TaskItem(
    val id: Int,
    val title: String,
    val dueDate: LocalDate?,

    //TODO: nice to haves
    //val priority: Priority,
)

 //{
    //fun isCompleted() = completedDate != null // stores the date the task was completed
    //fun completeColored() = if (isCompleted()) Color.Green else Color.Red // returns the color of the task

    // Add more fields as necessary, like dueDate, priority, etc.
//}
