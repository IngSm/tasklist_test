package tasklist.controller

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import tasklist.model.Task
import java.lang.reflect.ParameterizedType

class Serializer {
    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val type: ParameterizedType = Types.newParameterizedType(MutableList::class.java, Task::class.java)

    val taskListAdapter: JsonAdapter<MutableList<Task?>> = moshi.adapter(type)
}