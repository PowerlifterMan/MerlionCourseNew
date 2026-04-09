package com.example.merlioncoursenew.domain

import javax.inject.Inject

class GetCoursesUseCase @Inject constructor(
    private val repository: CourseRepository
) {
    suspend operator fun invoke(): List<Course>{
        return  repository.getCourseList()
    }
}
class UpdateCoursesUseCase @Inject constructor(
    private val repository: CourseRepository
) {
    suspend operator fun invoke(course: Course){
        repository.updateCourse(course)
    }
}