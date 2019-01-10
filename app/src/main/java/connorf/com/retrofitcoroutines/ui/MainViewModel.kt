package connorf.com.retrofitcoroutines.ui

import android.arch.lifecycle.ViewModel
import connorf.com.retrofitcoroutines.engine.model.Todo
import connorf.com.retrofitcoroutines.engine.repository.TodoRepository

class MainViewModel(private val todoRepo: TodoRepository): ViewModel() {
    val todos = todoRepo.get()

    fun updateTodo(todo: Todo) {
        todoRepo.updateTodo(todo)
    }
    fun update() {
        todoRepo.updateFromNetwork()
    }

    fun clearLocal() {
        todoRepo.clearLocalData()
    }

}