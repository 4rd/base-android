package connorf.com.retrofitcoroutines.engine.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitFactory {
    const val BASE_URL = "https://jsonplaceholder.typicode.com"

    fun makeRetrofitService(): ApiService = Retrofit.Builder()
                                                    .baseUrl(BASE_URL)
                                                    .addConverterFactory(MoshiConverterFactory.create())
                                                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                                                    .build().create(ApiService::class.java)
}