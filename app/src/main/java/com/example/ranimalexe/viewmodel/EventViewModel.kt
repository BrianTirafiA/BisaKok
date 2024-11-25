package com.example.ranimalexe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ranimalexe.model.EventItem

class EventViewModel : ViewModel() {
    private val _taskList = MutableLiveData<List<EventItem>>()
    val taskList: LiveData<List<EventItem>> get() = _taskList

    private val _progressCompleted = MutableLiveData<Int>(0)
    val progressCompleted: LiveData<Int> = _progressCompleted

    private val _progressRemaining = MutableLiveData<Int>(100)
    val progressRemaining: LiveData<Int> = _progressRemaining

    init {
        loadTasks()
        calculateProgress()
    }

    private fun loadTasks() {
        _taskList.value = listOf(
            EventItem(1, "Run of a lifetime", "Run 1000km", 10, false),
            EventItem(2, "Daily run", "Run 500m", 100, true),
            EventItem(3, "Daily challenges", "Run 10km", 50, false),
            EventItem(4, "It's Feeding time", "Feed your pet 3 times", 60, false),
        )
    }

    // Calculate progress based on the completion percentages of all tasks
    private fun calculateProgress() {
        val tasks = _taskList.value ?: emptyList()

        // Calculate the average completion percentage
        if (tasks.isNotEmpty()) {
            val totalCompletion = tasks.sumOf { it.completion }
            val averageCompletion = totalCompletion / tasks.size

            // Update progress values
            _progressCompleted.value = averageCompletion
            _progressRemaining.value = 100 - averageCompletion
        } else {
            // Default values if no tasks exist
            _progressCompleted.value = 0
            _progressRemaining.value = 100
        }
    }

    // Recalculate progress when a task is updated
    fun updateTaskCompletion(taskId: Int, newCompletion: Int) {
        val updatedTasks = _taskList.value?.map { task ->
            if (task.id == taskId) {
                task.copy(completion = newCompletion)
            } else {
                task
            }
        } ?: emptyList()

        _taskList.value = updatedTasks
        calculateProgress() // Recalculate progress
    }
}
