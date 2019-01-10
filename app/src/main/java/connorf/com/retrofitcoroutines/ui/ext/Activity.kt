package connorf.com.retrofitcoroutines.ui.ext

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity

inline fun <reified T: ViewModel> FragmentActivity.getViewModel(noinline creator: (() -> T)? = null): T {
    return creator?.let {
        ViewModelProviders.of(this, BaseViewModelFactory(creator)).get(T::class.java)
    } ?: ViewModelProviders.of(this).get(T::class.java)
}

class BaseViewModelFactory<T>(val creator: (() -> T)): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return creator() as T
    }
}