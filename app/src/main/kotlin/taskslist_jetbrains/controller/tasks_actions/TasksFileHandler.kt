package tasklist.controller.tasks_actions

import java.io.File
import kotlin.io.*

class TasksFileHandler {
    private val workingDir = System.getProperty("user.dir")
    private val separator = File.separator
    private val tasksListDir = File("${workingDir}${separator}CmdTasksList")
    private val fileName = "tasklist.json"

    fun saveTasks(json: String) {
        if (!tasksListDir.exists()) tasksListDir.mkdir()
        val file = File(tasksListDir, fileName)
        file.writeText(json)
    }

    fun readTasks(): String? {
        val jsonFile = File("${tasksListDir}${separator}$fileName")
        return if (jsonFile.exists()) jsonFile.readText() else null
    }
}