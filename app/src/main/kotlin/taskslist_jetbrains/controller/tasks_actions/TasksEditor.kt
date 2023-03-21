package tasklist.controller.tasks_actions

import kotlinx.datetime.LocalDate
import taskslist_jetbrains.taskList
import taskslist_jetbrains.tasksController
import java.lang.Exception

class TasksEditor {

    fun deleteTask() {
        if (taskList!!.isEmpty()) {
            println("No tasks have been input")
            return
        }
        println("Input the task number (1-${taskList!!.size}):")
        try {
            val taskNum = readln().toInt()
            taskList!!.removeAt(taskNum - 1)
            println("The task is deleted")
        } catch (e: Exception) {
            println("Invalid task number")
            deleteTask()
        }
    }

    private fun editTaskField(taskNum: Int) {
        println("Input a field to edit (priority, date, time, task):")
        when (readln()) {
            "priority" -> taskList!![taskNum]?.priority = tasksController.tasksAdder.addTaskPriority()
            "date" ->  taskList!![taskNum]?.date = LocalDate.parse(tasksController.tasksAdder.addTaskDate()).toString()
            "time" -> taskList!![taskNum]?.time = tasksController.tasksAdder.addTaskTime()
            "task" -> taskList!![taskNum]?.task = tasksController.tasksAdder.addTasksToTask()
            else -> {
                println("Invalid field")
                editTaskField(taskNum)
            }
        }
    }

    fun editTask() {
        if (taskList!!.isEmpty()) {
            println("No tasks have been input")
            return
        }
        println("Input the task number (1-${taskList!!.size}):")
        try {
            val taskNum = readln().toInt()
            if (taskList!!.indices.contains(taskNum - 1)) {
                editTaskField(taskNum - 1)
                println("The task is changed")
            } else {
                println("Invalid task number")
                editTask()
            }
        } catch (e: Exception) {
            println("Invalid task number")
            editTask()
        }
    }
}