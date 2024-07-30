package uk.ac.tees.mad.w9624019.newmealapp.viewmodels.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import uk.ac.tees.mad.w9624019.newmealapp.model.MealsRepository
import uk.ac.tees.mad.w9624019.newmealapp.model.response.Category

class MealDetailsViewModel(
    private val savedStateHandle: SavedStateHandle,

) : ViewModel() {
    private val repository: MealsRepository = MealsRepository.getInstance()
    var mealState = mutableStateOf<Category?>(null)

    init {
        val mealID = savedStateHandle.get<String>("meal_category_id")?:""
        mealState.value = repository.getMeal(mealID)

    }

}