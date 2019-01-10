package connorf.com.retrofitcoroutines.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import connorf.com.retrofitcoroutines.R
import connorf.com.retrofitcoroutines.engine.model.Todo
import kotlinx.android.synthetic.main.todo_card.view.*

class TodoListAdapter(var list: List<Todo> = emptyList(), val callback: (Todo) -> Unit): RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {
    class TodoViewHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TodoViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.todo_card, p0, false)
        return TodoViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TodoViewHolder, index: Int) {
        val todo = list[index]

        with(holder.view) {
            tv_title.text = todo.title
            tb_done.isChecked = !todo.completed
            tb_done.setOnClickListener {
                todo.apply { completed = !completed }
                callback(todo)
            }
        }
    }

    fun updateData(newData: List<Todo>?) {
        newData?.let {
            list = newData
            notifyDataSetChanged()
        } ?: run{ list = listOf() }
    }
}