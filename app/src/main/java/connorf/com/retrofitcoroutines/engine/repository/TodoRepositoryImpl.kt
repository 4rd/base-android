package connorf.com.retrofitcoroutines.engine.repository

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import connorf.com.retrofitcoroutines.engine.db.TodoDao
import connorf.com.retrofitcoroutines.engine.model.Todo
import connorf.com.retrofitcoroutines.engine.network.ApiService
import connorf.com.retrofitcoroutines.engine.network.makeSimpleRequest
import kotlinx.coroutines.*

//could also have a dao as a param
class TodoRepositoryImpl(api: ApiService, db: TodoDao): BaseRepository(api, db), TodoRepository {
    override fun get(): LiveData<List<Todo>> = db.getAllTodos()

    @WorkerThread
    suspend fun update(todo: Todo) = db.update(todo)

    @WorkerThread
    suspend fun insert(todo: Todo) = db.insert(todo)

    @WorkerThread
    suspend fun deleteAll() = db.deleteAll()

    override fun updateFromNetwork() {
        GlobalScope.launch(Dispatchers.IO) {
            val deletion = async { deleteAll() }
            deletion.await()

            makeSimpleRequest({ api.getTodos(1) },
                    {  it?.forEach { todo -> runBlocking { insert(todo) } } },
                    {})
        }
    }

    override fun clearLocalData() {
        GlobalScope.launch { deleteAll() }
    }

    override fun updateTodo(todo: Todo) {
        GlobalScope.launch(Dispatchers.IO) { insert(todo) }
    }
}