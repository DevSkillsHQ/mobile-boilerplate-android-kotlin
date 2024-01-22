package io.alvalabs.app.fragments.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Response
import com.example.domain.model.Meal
import com.example.domain.repository.FoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel (
    private val repo: FoodRepository
): ViewModel() {

    private val _meal: MutableStateFlow<Response<Meal>?> = MutableStateFlow(null)
    val meal: StateFlow<Response<Meal>?> = _meal.asStateFlow()

    fun getMeal(id: Int) {
        viewModelScope.launch {
            repo.getMealWithId(id).collect { response ->
                _meal.update {
                    response
                }
            }
        }
    }

}