package com.example.merlioncoursenew.domain

interface CourseRepository {
    suspend fun getCourseList(): List<Course>
    suspend fun updateCourse(course: Course)

}