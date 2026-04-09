package com.example.merlioncoursenew.data.remote

import com.example.merlioncoursenew.domain.Course
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CoursesDTO(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("text") val description: String,
    @SerialName("price") val price: String,
    @SerialName("rate") val rate: String,
    @SerialName("startDate") val startDate: String,
    @SerialName("hasLike") val hasLike: Boolean,
    @SerialName("publishDate") val publishDate: String
) {
}
fun CoursesDTO.toCourse(): Course{
    return Course(
        id = this.id,
        title = this.title,
        description = this.description,
        price = this.price.toInt(),
        rate = this.rate.toDouble(),
        startDate = this.startDate,
        hasLike = this.hasLike,
        publishDate = this.publishDate
    )
}
fun List<CoursesDTO>.toListCourse(): List<Course>{
    return this.map {
        it.toCourse()
    }
}