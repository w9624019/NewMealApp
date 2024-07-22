package uk.ac.tees.mad.w9624019.newmealapp.model

data class UserModel(
    val uid: String? = "",
    val name: String = "",
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val bio: String = "",
    val imageUrl: String = "",
    val createdAt: String = "",
    val isPrivate: Boolean = true
)
