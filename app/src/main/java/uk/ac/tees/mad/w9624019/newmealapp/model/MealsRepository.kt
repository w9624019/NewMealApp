package uk.ac.tees.mad.w9624019.newmealapp.model

import uk.ac.tees.mad.w9624019.newmealapp.api.MealsWebService
import uk.ac.tees.mad.w9624019.newmealapp.model.response.Category
import uk.ac.tees.mad.w9624019.newmealapp.model.response.MealsCategoriesResponse

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {
    companion object{
        @Volatile
        private var instance: MealsRepository?=null

        fun getInstance()= instance ?: synchronized(this){
            instance ?: MealsRepository().also {
                instance =it
            }
        }

    }

    private var cacheMeals= listOf<Category>()

    suspend fun getMeals(): MealsCategoriesResponse {
        var response=webService.getMeals()
        cacheMeals=response.categories
        return response

    }

    fun getMeal(mealID:String): Category?{
        return cacheMeals.firstOrNull {
            it.idCategory==mealID
        }
    }
}