package com.example.merlioncoursenew.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Transaction
    @Insert(onConflict = REPLACE)
    suspend fun addCourses(courses: List<CourseDbModel>): List<Long>

    @Transaction
    @Insert(onConflict = REPLACE)
    suspend fun addNewCourse(course: CourseDbModel): Long

    @Transaction
    @Query("UPDATE education_courses SET has_like = NOT has_like WHERE id ==:idCourse")
    suspend fun switchLiked(idCourse: Int)

    @Transaction
    @Query("SELECT * FROM education_courses")
    suspend fun getAllCourses(): List<CourseDbModel>

}