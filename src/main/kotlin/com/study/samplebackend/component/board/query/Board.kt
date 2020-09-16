package com.study.samplebackend.component.board.query

enum class Priority {
    LOW, MEDIUM, HIGH
}

data class Board(
    val id: Int,
    val boardName: String,
    var group: String,
    var description: String,
    val tasks: List<Task>? = listOf()
)

data class Task(
    val taskId: String,
    val taskName: String,
    val description: String,
    val priority: Priority,
    val subTasks: List<SubTask>? = listOf()
)

data class SubTask(
    val subTaskId: String,
    val subTaskName: String,
    val description: String
)
