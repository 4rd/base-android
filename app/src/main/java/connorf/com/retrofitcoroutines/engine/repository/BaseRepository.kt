package connorf.com.retrofitcoroutines.engine.repository

import connorf.com.retrofitcoroutines.engine.db.TodoDao
import connorf.com.retrofitcoroutines.engine.network.ApiService

abstract class BaseRepository(val api: ApiService, val db: TodoDao) {
}