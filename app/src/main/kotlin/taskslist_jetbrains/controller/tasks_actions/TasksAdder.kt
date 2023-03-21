package tasklist.controller.tasks_actions

import kotlinx.datetime.*
import taskslist_jetbrains.taskList
import java.lang.Exception
import java.util.*

import tasklist.model.Task
import java.time.LocalTime

class TasksAdder() {
    lateinit var newTask: Task
    private lateinit var taskPriority: String
    private lateinit var taskDate: String
    private lateinit var taskTime: String

    private var innerLineTasks = mutableListOf<String>()
    private val priorities = "chnl"

    fun addTaskPriority(): String {
        println("Input the task priority (C, H, N, L):")
        val priority = readln()
        return if (priority.lowercase() !in priorities || priority.isEmpty()) {
            addTaskPriority()
        } else {
            taskPriority = priority
            return priority
        }
    }

    fun addTaskDate(): String {
        println("Input the date (yyyy-mm-dd):")
        val strDate = readln().split("-")
        try {
            val date = LocalDate(strDate[0].toInt(), strDate[1].toInt(), strDate[2].toInt())
            taskDate = date.toString()
        } catch (e: Exception) {
            println("The input date is invalid")
            addTaskDate()
        }
        return taskDate
    }

    fun addTaskTime(): String {
        println("Input the time (hh:mm):")
        val strTime = readln().split(":")
        try {
            val time = LocalTime.of(strTime[0].toInt(), strTime[1].toInt())
            taskTime = time.toString()
        } catch (e: Exception) {
            println("The input time is invalid")
            addTaskTime()
        }
        return taskTime
    }

    fun addTasksToTask(): MutableList<String> {
        val todos = mutableListOf<String>()
        println("Input a new task (enter a blank line to end):")
        val scanner = Scanner(System.`in`)
        while (scanner.hasNextLine()) {
            val nxt = scanner.nextLine().trimStart()
            if (nxt.isEmpty() || nxt.isBlank()) {
                if (todos.isEmpty()) println("The task is blank")
                break
            } else {
                if (nxt.length >= 44) {
                    val ending = nxt.substring(44)
                    val start = nxt.substring(0, 44)
                    val stringArr = arrayOf(start, ending)
                    stringArr.forEach {
                        todos.add(it)
                    }
                } else {
                    todos.add(nxt)
                }
            }
        }
        if (todos.isNotEmpty()) innerLineTasks = todos
        return innerLineTasks
    }

    fun addTask() {
        addTaskPriority()
        addTaskDate()
        addTaskTime()
        addTasksToTask()
        if (innerLineTasks.isNotEmpty()) {
            newTask = Task(taskPriority, LocalDate.parse(taskDate).toString(), taskTime, innerLineTasks)
            taskList!!.add(newTask)
        } else {
            return
        }
    }
}