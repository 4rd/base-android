package connorf.com.retrofitcoroutines.engine.network

import okhttp3.ResponseBody

interface RequestCallback<T> {
    fun onSuccess(body: T?)
    fun onBadRequest() { onFailure() }
    fun onClientError(response: ResponseBody?) { onFailure() }
    fun onServerError() { onFailure() }
    fun onUnauthorized() { onFailure() }
    fun onForbidden() { onFailure() }
    fun onFailure()
}
