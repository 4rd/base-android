package connorf.com.retrofitcoroutines.ui

import android.arch.lifecycle.ViewModel
import connorf.com.retrofitcoroutines.engine.App
import connorf.com.retrofitcoroutines.engine.model.Todo

class MainViewModel: ViewModel() {
    val todos = App.todoRepo.get()

    fun updateTodo(todo: Todo) {
        App.todoRepo.updateTodo(todo)
    }
    fun update() {
        App.todoRepo.updateFromNetwork()
    }

    fun clearLocal() {
        App.todoRepo.clearLocalData()
    }

}