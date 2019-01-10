package connorf.com.retrofitcoroutines.engine.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import connorf.com.retrofitcoroutines.engine.model.Todo

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todo: Todo)

    @Update
    fun update(todo: Todo)

    @Query("DELETE FROM todo_table")
    fun deleteAll()

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllTodos(): LiveData<List<Todo>>
}