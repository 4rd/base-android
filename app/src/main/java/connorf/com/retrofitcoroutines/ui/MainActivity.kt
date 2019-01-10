package connorf.com.retrofitcoroutines.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import connorf.com.retrofitcoroutines.R
import connorf.com.retrofitcoroutines.ui.ext.getViewModel
import connorf.com.retrofitcoroutines.ui.ext.reObserve
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {
    private val model: MainViewModel by lazy { getViewModel<MainViewModel>() }
    private val adapter: TodoListAdapter?
        get() = rv_todos?.adapter as? TodoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_todos.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = TodoListAdapter { model.updateTodo(it) }
        }

        btn_fetch.setOnClickListener { model.update() }
        btn_clear.setOnClickListener { model.clearLocal() }

        model.todos.reObserve(this, Observer { adapter?.updateData(it) })
    }
}

