package tasklist.model

import kotlinx.datetime.LocalDate

class Task(var priority: String, var date: String, var time: String, var task: MutableList<String>)