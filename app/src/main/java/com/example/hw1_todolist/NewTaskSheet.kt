package com.example.hw1_todolist

import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.hw1_todolist.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat.CLOCK_24H// This is what IDE suggested
import java.time.LocalTime

// This represents everything going on in the pop up, fragment sheet


class NewTaskSheet(private val taskItem: TaskItem?) : BottomSheetDialogFragment() {

    private lateinit var taskViewModel: TaskViewModel
    private var dueTime: LocalTime? = null

    private lateinit var taskTitle: TextView
    private lateinit var nameEditText: EditText
    private lateinit var descEditText: EditText
    private lateinit var timePickerButton: Button
    private lateinit var saveButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.fragment_new_task_sheet,
            container,
            false
        ) // the fragment_new_task_sheet.xml file
        taskViewModel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)


        taskTitle = view.findViewById(R.id.etTaskTitle)
        nameEditText = view.findViewById(R.id.etName)
        descEditText = view.findViewById(R.id.etDesc)
        timePickerButton = view.findViewById(R.id.btnTimePicker)
        saveButton = view.findViewById(R.id.btnSaveTask)

        initializeUI()
        setupListeners()

        return view
    }

    private fun initializeUI() {
        if (taskItem != null) {
            taskTitle.text = "Edit Task"
            nameEditText.setText(taskItem.name)
            descEditText.setText(taskItem.desc)
        } else {
            taskTitle.text = "New Task"
        }
    }

// How the new task is saved from user input
    private fun setupListeners() {
        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val desc = descEditText.text.toString()

            if (name.isNotEmpty() && desc.isNotEmpty()) {
                if (taskItem == null) {
                    taskViewModel.addTaskItem(name, desc, null, null)
                    dismiss()
                } else {
                    taskViewModel.updateTaskItem(taskItem.id, name, desc, null)
                    dismiss()
                }
            } else {
                // prompt user to fill out all fields

            }
        }
        timePickerButton.setOnClickListener {
            openTimePicker()
        }
    }


    private fun openTimePicker() {
        val currentTime = dueTime ?: LocalTime.now()
        val listener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            dueTime = LocalTime.of(hour, minute)
            updateTimeButtonText()
        }
        TimePickerDialog(
            requireContext(),
            listener,
            currentTime.hour,
            currentTime.minute,
            true
        ).apply {
            setTitle("Select Due Time")
            show()
        }
    }


    private fun updateTimeButtonText() {
        dueTime?.let {
            timePickerButton.text = String.format("%02d:%02d", it.hour, it.minute)
        }
    }
}
