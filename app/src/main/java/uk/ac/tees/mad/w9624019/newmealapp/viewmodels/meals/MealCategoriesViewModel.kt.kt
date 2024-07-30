package uk.ac.tees.mad.w9624019.newmealapp.viewmodels.meals

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import uk.ac.tees.mad.w9624019.newmealapp.model.MealsRepository
import uk.ac.tees.mad.w9624019.newmealapp.model.response.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MealCategoriesViewModel(private  val repository: MealsRepository = MealsRepository.getInstance()):ViewModel() {

    //creating our own scope, so that we have more control over the coroutine
    private val mealsJob=Job()

   init {
       Log.d("TAG_coroutines","About to launch coroutine")
       // val scope= CoroutineScope(mealsJob+Dispatchers.IO)
       viewModelScope.launch(Dispatchers.IO) {
           Log.d("TAG_coroutines","launched coroutine")
           val meals=getMealsFromRepo()
           Log.d("TAG_coroutines","received async data")
           mealsState.value=meals
       }
       Log.d("TAG_coroutines","other work")
   }

    val mealsState: MutableState<List<Category>> =  mutableStateOf(emptyList<Category>())

    private suspend fun getMealsFromRepo():List<Category> {
         return repository.getMeals().categories
    }
}