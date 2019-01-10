package connorf.com.retrofitcoroutines.engine

import android.app.Application
import connorf.com.retrofitcoroutines.engine.db.TodoDao
import connorf.com.retrofitcoroutines.engine.db.TodoRoomDatabase
import connorf.com.retrofitcoroutines.engine.network.RetrofitFactory
import connorf.com.retrofitcoroutines.engine.repository.TodoRepository
import connorf.com.retrofitcoroutines.engine.repository.TodoRepositoryImpl

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        val api = RetrofitFactory.makeRetrofitService()
        val db = TodoRoomDatabase.getDatabase(this).todoDao()
        todoRepo = TodoRepositoryImpl(api, db)
    }

    companion object {
        lateinit var todoRepo: TodoRepository
    }
}