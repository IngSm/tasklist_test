package tasklist.controller

import tasklist.controller.tasks_actions.TasksAdder
import tasklist.controller.tasks_actions.TasksEditor
import tasklist.controller.tasks_actions.TasksPrinter
import tasklist.controller.tasks_actions.TasksFileHandler
import tasklist.model.Task
import taskslist_jetbrains.tasksController
import taskslist_jetbrains.taskList
import kotlin.system.exitProcess

class TasksController {
    val tasksAdder = TasksAdder()
    val tasksFileHandler = TasksFileHandler()
    val serializer = Serializer()

    private val tasksPrinter = TasksPrinter()
    private val tasksEditor = TasksEditor()

    fun makeAction() {
        println("Input an action (add, print, edit, delete, end):")
        when (readln()) {
            "end" -> {
                val nTaskList: MutableList<Task?> = taskList?.toMutableList() ?: mutableListOf()
                if (taskList!!.isNotEmpty()) tasksFileHandler.saveTasks(serializer.taskListAdapter.toJson(nTaskList))
                println("Tasklist exiting!")
                exitProcess(0)
            }
            "print" -> tasksPrinter.printTasks()
            "add" -> tasksAdder.addTask()
            "edit" -> {
                if (taskList?.isNotEmpty() == true) tasksController.tasksPrinter.printTasks()
                tasksEditor.editTask()
            }
            "delete" -> {
                if (taskList?.isNotEmpty() == true) tasksController.tasksPrinter.printTasks()
                tasksEditor.deleteTask()
            }
            else -> println("The input action is invalid")
        }
        makeAction()
    }
}