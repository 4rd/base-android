package connorf.com.retrofitcoroutines.engine.network

import kotlinx.coroutines.*
import retrofit2.Response

fun <ApiResp> makeRequest(apiRequest: () -> Deferred<Response<ApiResp>>,
                              handler: RequestCallback<ApiResp>) {
    GlobalScope.launch(Dispatchers.IO) {
        val request = apiRequest()
        try {
            val response = request.await()

            when(response.code()) {
                200 -> handler.onSuccess(response.body())
                400 -> handler.onBadRequest()
                401 -> handler.onUnauthorized()
                403 -> handler.onForbidden()
                in 400..499 -> handler.onClientError(response.errorBody())
                in 500..599 -> handler.onServerError()
                else -> handler.onFailure()
            }
            handler.onSuccess(response.body())
        } catch (e: Throwable) {
            handler.onFailure()
        }
    }
}

fun <ApiResp> makeSimpleRequest(apiRequest: () -> Deferred<Response<ApiResp>>,
                                    onSuccess: (ApiResp?) -> Unit,
                                    onFailure: () -> Unit) {
    val handler = object : RequestCallback<ApiResp> {
        override fun onSuccess(body: ApiResp?) { onSuccess(body) }
        override fun onFailure() { onFailure() }
    }

    return makeRequest(apiRequest, handler)
}
