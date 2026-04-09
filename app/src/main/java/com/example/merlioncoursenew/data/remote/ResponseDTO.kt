package com.example.merlioncoursenew.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDTO(
    @SerialName("courses") val courses: List<CoursesDTO>
)
