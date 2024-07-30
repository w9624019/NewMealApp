package uk.ac.tees.mad.w9624019.newmealapp.navigation

sealed class Routes(val route: String) {

    object Home : Routes("home")
    object Splash : Routes("splash")
    object BottomNav : Routes("bottom_nav")

    object Login : Routes("login")
    object Register : Routes("register")

    object Setting : Routes("setting")
    object DestList : Routes("dest_list")


}
