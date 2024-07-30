package uk.ac.tees.mad.w9624019.newmealapp.util

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import at.favre.lib.crypto.bcrypt.BCrypt
import java.text.SimpleDateFormat
import java.util.Date

class Util {

    companion object {
        fun goTo(
            navHostController: NavHostController,
            route: String,
        ) {
            navHostController.navigate(route) {
                popUpTo(navHostController.graph.startDestinationId) {
                    inclusive = true
                }
            }
        }

        fun showToast(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        fun generateHashPassword(password: String): String {
            return BCrypt.withDefaults().hashToString(12, password.toCharArray())
        }

        fun verifyPassword(password: String, hashedPassword: String): Boolean {
            return BCrypt.verifyer().verify(password.toCharArray(), hashedPassword).verified
        }

        fun convertDateToString(date: Date): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            return inputFormat.format(date)
        }

    }

}