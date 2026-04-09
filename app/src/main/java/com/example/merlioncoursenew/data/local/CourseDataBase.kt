package com.example.merlioncoursenew.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.hilt.android.qualifiers.ApplicationContext

@Database(
    entities = [CourseDbModel::class],
    version = 1,
    exportSchema = false
)
abstract class CourseDataBase : RoomDatabase() {
    abstract fun courseDao(): Dao

    companion object {
        private var instance: CourseDataBase? = null
        private var LOCK = Any()

        fun getInstance(@ApplicationContext context: Context): CourseDataBase {
            instance?.let { return it }
            synchronized(LOCK) {
                instance?.let { return it }
                return Room.databaseBuilder<CourseDataBase>(
                    context = context,
                    klass = CourseDataBase::class.java,
                    name = "notesDb.db"
                ).fallbackToDestructiveMigration(true)
                    .build()
                    .also { instance = it }
            }

        }
    }
}

