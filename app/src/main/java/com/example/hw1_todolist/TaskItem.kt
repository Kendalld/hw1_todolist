package com.example.hw1_todolist

import java.time.LocalDate
import java.time.LocalTime

// TaskItem: Represents the data structure for a task in your to-do list.
// IMPORTANT: This class must be well-defined; it's the blueprint for your task data.
data class TaskItem(
    var name: String,
    var desc: String,
    var dueTime: LocalTime?,
    var completedDate: LocalDate? = null, // defaulted to null
    // NewTaskSheet has this implemented to add very simple rolling counter
    // It would be better if I used a UUID and spool for this
    val id: Int
)

