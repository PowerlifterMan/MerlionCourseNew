package com.example.merlioncoursenew.data

import android.content.Context
import com.example.merlioncoursenew.data.local.Dao
import com.example.merlioncoursenew.data.remote.ApiService
import com.example.merlioncoursenew.data.remote.toListCourse
import com.example.merlioncoursenew.domain.Course
import com.example.merlioncoursenew.domain.CourseRepository
import com.example.merlioncoursenew.domain.toCourseDbModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val apiService: ApiService,
    private val courseDao: Dao
) : CourseRepository {

    override suspend fun getCourseList(): List<Course> {
        val coursesList = loadCoursesFromRemote()
        courseDao.addCourses(coursesList.map { it.toCourseDbModel() })
        return coursesList
    }

    override suspend fun updateCourse(course: Course) {
        courseDao.addNewCourse(course.toCourseDbModel())
    }

    private suspend fun loadCoursesFromRemote(): List<Course>{
        return try {
            apiService.loadCourses().courses.toListCourse()
        } catch (e: Exception){
            throw e
        }
    }
}