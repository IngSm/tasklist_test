package tasklist.controller.tasks_actions

import kotlinx.datetime.*
import taskslist_jetbrains.taskList

class TasksPrinter {

    private val leftAlignFormat = "| %-2h | %-2s | %-1s | %-1s | %-1s |%-44s|\n"

    private fun makeTaskDeadline(date: String): Comparable<*> {
        val currentDate = Clock.System.now().toLocalDateTime(TimeZone.of("UTC+0")).date
        val dateDiff = currentDate.daysUntil(LocalDate.parse(date))
        return if (dateDiff == 0) {
            "\u001B[103m \u001B[0m"
        } else if (dateDiff > 0) {
            "\u001B[102m \u001B[0m"
        } else {
            "\u001B[101m \u001B[0m"
        }
    }

    private fun makePriorityColor(priority: String): String {
        return when (priority.lowercase()) {
            "c" -> "\u001B[101m \u001B[0m"
            "h" -> "\u001B[103m \u001B[0m"
            "n" -> "\u001B[102m \u001B[0m"
            "l" -> "\u001B[104m \u001B[0m"
            else -> ""
        }
    }

    fun printTasks() {
        if (taskList!!.isEmpty()) {
            println("No tasks have been input")
        } else {
            System.out.format("+----+------------+-------+---+---+--------------------------------------------+\n")
            System.out.format("| N  |    Date    | Time  | P | D |                   Task                     |\n")
            System.out.format("+----+------------+-------+---+---+--------------------------------------------+\n")
            taskList?.forEachIndexed { index, s ->
                val chunked = s!!.task.flatMap { it.chunked(44).map { it2 -> it2.padEnd(44) } }
                System.out.format(leftAlignFormat, index + 1, s.date.toString(), s.time, makePriorityColor(s.priority), makeTaskDeadline(s.date), s.task[0])
                System.out.format(chunked.drop(1).joinToString("") { "|    |            |       |   |   |${it}|\n" })
                System.out.format("+----+------------+-------+---+---+--------------------------------------------+\n")
            }
        }
    }
}