package connorf.com.retrofitcoroutines.engine.repository

import android.arch.lifecycle.LiveData
import connorf.com.retrofitcoroutines.engine.model.Todo

interface TodoRepository {
    fun get(): LiveData<List<Todo>>
    fun updateFromNetwork()
    fun clearLocalData()
    fun updateTodo(todo: Todo)
}