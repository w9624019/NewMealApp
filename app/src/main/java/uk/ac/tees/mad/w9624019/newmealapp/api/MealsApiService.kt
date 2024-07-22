package uk.ac.tees.mad.w9624019.newmealapp.api

import uk.ac.tees.mad.w9624019.newmealapp.model.response.MealsCategoriesResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


class MealsWebService {

    private var mealsApi: MealsAppService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
         mealsApi = retrofit.create(MealsAppService::class.java)
    }

    interface MealsAppService {
        @GET("categories.php")
        suspend fun getMealsCategoriesApi(): MealsCategoriesResponse
    }
    //Api Call methods that wil be accessed from View models
    suspend fun getMeals(): MealsCategoriesResponse {
        return mealsApi.getMealsCategoriesApi()
    }

}