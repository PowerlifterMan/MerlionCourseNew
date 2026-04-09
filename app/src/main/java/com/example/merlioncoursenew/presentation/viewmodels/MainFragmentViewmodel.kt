package com.example.merlioncoursenew.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merlioncoursenew.domain.Course
import com.example.merlioncoursenew.domain.GetCoursesUseCase
import com.example.merlioncoursenew.domain.UpdateCoursesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewmodel @Inject constructor (
    private val getCoursesUseCase: GetCoursesUseCase,
    private val updateCoursesUseCase: UpdateCoursesUseCase
) : ViewModel() {
    private val query = MutableStateFlow("")
    private var _state = MutableStateFlow(MainScreenState())
    val state get() = _state.asStateFlow()

    init {
        query.onEach { input ->
            _state.update { it.copy(query = input) }
        }.map { input ->
            getCoursesUseCase()
        }.map { list ->
            _state.update { it.copy(courseList = list) }
        }.flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }


}

sealed interface MainScreenCommand {
    data class InputSearchQuery(val query: String) : MainScreenCommand
    data class SwitchLikeStatus(val courseId: Int) : MainScreenCommand
    data object SwitchOrder : MainScreenCommand
}

data class MainScreenState(
    val query: String = "",
    val courseList: List<Course> = listOf()
)