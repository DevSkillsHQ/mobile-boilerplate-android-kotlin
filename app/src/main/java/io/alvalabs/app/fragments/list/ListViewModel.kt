package io.alvalabs.app.fragments.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Response
import com.example.domain.model.Meal
import com.example.domain.repository.FoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListViewModel(
    private val repo: FoodRepository
): ViewModel() {

    private val _meals: MutableStateFlow<Response<List<Meal>>?> = MutableStateFlow(null)
    val meals: StateFlow<Response<List<Meal>>?> = _meals.asStateFlow()

    private var offset = 4
    init {
        getMeals()
    }

    fun refresh() {
        offset += 4
        getMeals()
    }

    private fun getMeals() {
        viewModelScope.launch {
            repo.getMeals(4, offset).collect { response ->
                _meals.update {
                    response
                }
            }
        }
    }
}