package connorf.com.retrofitcoroutines.engine.network

import connorf.com.retrofitcoroutines.engine.model.Todo
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/todos")
    fun getTodos(@Query("userId") userId: Int): Deferred<Response<List<Todo>>>
}