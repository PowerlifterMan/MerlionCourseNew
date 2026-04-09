package com.example.merlioncoursenew.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("u/0/uc?i")
    suspend fun loadCourses(
        @Query("id") id: String = "15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q",
        @Query("export") export: String = "download"
            ): ResponseDTO
}