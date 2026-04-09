package com.example.merlioncoursenew.di
import android.content.Context
import com.example.merlioncoursenew.data.CourseRepositoryImpl
import com.example.merlioncoursenew.data.local.CourseDataBase
import com.example.merlioncoursenew.data.local.Dao
import com.example.merlioncoursenew.data.remote.ApiService
import com.example.merlioncoursenew.domain.CourseRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DiModule {
    @Binds
    @Singleton
    fun bindRepository(impl: CourseRepositoryImpl): CourseRepository
    companion object {



        @Singleton
        @Provides
        fun provideDataBase(@ApplicationContext context: Context): CourseDataBase{
            return CourseDataBase.getInstance(context)
        }

        @Singleton
        @Provides
        fun provideDao(dataBase: CourseDataBase): Dao{
            return dataBase.courseDao()
        }

        @Provides
        fun logging(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)
        }
        @Provides
        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder().addInterceptor(logging()).build()
        }
        @Provides
        @Singleton
        fun provideApiService(): ApiService {
            val baseUrl = "https://drive.usercontent.google.com/"
            val converter = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }.asConverterFactory(
                "application/json".toMediaType()
            )
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converter)
                .client(provideOkHttpClient())
                .build()
            return retrofit.create<ApiService>()
        }
    }
}