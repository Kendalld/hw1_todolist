package com.example.hw1_todolist

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalTime

class NewTaskSheet(private val taskItem: TaskItem?) : BottomSheetDialogFragment() {

    private lateinit var taskViewModel: TaskViewModel
    private var dueTime: LocalTime? = null

    private lateinit var taskTitle: TextView
    private lateinit var nameEditText: EditText
    private lateinit var descEditText: EditText
    private lateinit var timePickerButton: Button
    private lateinit var saveButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new_task_sheet, container, false)
        taskViewModel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)

        // Initialize all view components
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
            taskItem.dueTime?.let {
                dueTime = it
                updateTimeButtonText()
            }
        } else {
            taskTitle.text = "New Task"
        }
    }

    private fun setupListeners() {
        saveButton.setOnClickListener {
            saveAction()
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

    private fun saveAction() {
        val name = nameEditText.text.toString().trim()
        val desc = descEditText.text.toString().trim()

        if (name.isNotEmpty() && desc.isNotEmpty()) {
            if (taskItem == null) {
                taskViewModel.addTaskItem(name, desc, dueTime, null)
            } else {
                taskViewModel.updateTaskItem(taskItem.id, name, desc, dueTime)
            }
            dismiss()
        } else {
            Toast.makeText(context, "Please fill out all fields", Toast.LENGTH_SHORT).show()
        }
    }
}