package uk.ac.tees.mad.w9624019.newmealapp.util

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Date

class Util {
    companion object {
        fun showToast(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
        fun convertDateToString(date: Date): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            return inputFormat.format(date)
        }
    }

}