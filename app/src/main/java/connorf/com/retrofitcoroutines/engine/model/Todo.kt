package connorf.com.retrofitcoroutines.engine.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "todo_table")
data class Todo(val userId: Int, @PrimaryKey val id: Int, var title: String, var completed: Boolean)
