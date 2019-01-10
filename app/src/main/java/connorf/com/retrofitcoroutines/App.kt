package connorf.com.retrofitcoroutines

import android.app.Application
import connorf.com.retrofitcoroutines.engine.db.TodoRoomDatabase
import connorf.com.retrofitcoroutines.engine.network.RetrofitFactory
import connorf.com.retrofitcoroutines.engine.repository.TodoRepository
import connorf.com.retrofitcoroutines.engine.repository.TodoRepositoryImpl
import connorf.com.retrofitcoroutines.ui.MainViewModel
import org.koin.android.ext.android.startKoin
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

class App: Application() {
    private val appModule = module {
        single { RetrofitFactory.makeRetrofitService() }
        single { TodoRoomDatabase.getDatabase(this@App).todoDao() }
        single<TodoRepository> { TodoRepositoryImpl(get(), get()) }

        viewModel { MainViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule))
    }
}