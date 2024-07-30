package uk.ac.tees.mad.w9624019.newmealapp.model.response

data class MealsCategoriesResponse(
    val categories: List<Category>,
)

data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String,
)