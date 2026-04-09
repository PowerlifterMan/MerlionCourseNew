package com.example.merlioncoursenew.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.merlioncoursenew.domain.Course
import kotlinx.serialization.SerialName

@Entity(tableName = "education_courses")
data class CourseDbModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("description") val description: String,
    @ColumnInfo("price") val price: Int,
    @ColumnInfo("rating") val rate: Double,
    @ColumnInfo("start_date") val startDate: String,
    @ColumnInfo("has_like") val hasLike: Boolean,
    @ColumnInfo("publish_date") val publishDate: String
)

fun CourseDbModel.toCourse(): Course{
    return Course(
        id = this.id,
        title = this.title,
        description = this.description,
        price = this.price,
        rate = this.rate,
        startDate = this.startDate,
        hasLike = this.hasLike,
        publishDate = this.publishDate
    )
}