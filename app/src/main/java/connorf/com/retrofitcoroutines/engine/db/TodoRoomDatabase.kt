package connorf.com.retrofitcoroutines.engine.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import connorf.com.retrofitcoroutines.engine.model.Todo

@Database(entities = [Todo::class], version = 1)
abstract class TodoRoomDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile
        private var INSTANCE: TodoRoomDatabase? = null

        fun getDatabase(context: Context): TodoRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        TodoRoomDatabase::class.java,
                        "todo_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}