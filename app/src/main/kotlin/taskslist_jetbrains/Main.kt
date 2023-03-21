package taskslist_jetbrains

import tasklist.model.Task
import tasklist.controller.TasksController

var taskList: MutableList<Task?>? = mutableListOf()
val tasksController = TasksController()

class Main {
}

fun main() {
    val tasks = tasksController.tasksFileHandler.readTasks()
    if (tasks != null) {
        val fileTasksList = tasksController.serializer.taskListAdapter.fromJson(tasks)
        taskList = fileTasksList
    }
    tasksController.makeAction()
}