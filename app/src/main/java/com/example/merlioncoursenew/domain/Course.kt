package com.example.merlioncoursenew.domain

import com.example.merlioncoursenew.data.local.CourseDbModel

data class Course(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val rate: Double,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)

fun Course.toCourseDbModel(): CourseDbModel{
    return CourseDbModel(
        this.id,
        this.title,
        this.description,
        this.price,
        this.rate,
        this.startDate,
        this.hasLike,
        this.publishDate
    )

}