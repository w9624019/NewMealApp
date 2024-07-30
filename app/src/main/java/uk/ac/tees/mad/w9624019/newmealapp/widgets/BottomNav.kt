package uk.ac.tees.mad.w9624019.newmealapp.widgets

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import uk.ac.tees.mad.w9624019.newmealapp.model.BottomNavItem
import uk.ac.tees.mad.w9624019.newmealapp.navigation.Routes
import uk.ac.tees.mad.w9624019.newmealapp.screens.NotificationScreen
import uk.ac.tees.mad.w9624019.newmealapp.screens.ProfileScreen
import uk.ac.tees.mad.w9624019.newmealapp.screens.SearchScreen
import uk.ac.tees.mad.w9624019.newmealapp.screens.SettingScreen
import uk.ac.tees.mad.w9624019.newmealapp.viewmodels.details.MealDetailsViewModel
import uk.ac.tees.mad.w9624019.newmealapp.viewmodels.meals.MealsCategoriesScreen
import uk.ac.tees.mad.w9624019.newmealapp.viewmodels.details.MealDetailsScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomNavbar(navController: NavHostController) {
    val controller = rememberNavController() //local scope controller for bottom icon routing

    Scaffold(bottomBar = { CustomBottomNavBar(controller) }) {
        NavHost(
            navController = controller,
            startDestination = Routes.Home.route,
            modifier = Modifier.padding(it)
        ) {

            composable(Routes.Home.route) {
                MealsCategoriesScreen { navigationMealID ->
                    controller.navigate(route = "dest_detail/$navigationMealID")
                }
            }
            composable(
                route = "dest_detail/{meal_category_id}",
                arguments = listOf(navArgument(name = "meal_category_id") {
                    type = NavType.StringType
                })
            ) {
                val viewModel: MealDetailsViewModel = viewModel()
                MealDetailsScreen(viewModel.mealState.value)
            }
            composable(Routes.SearchThread.route) {
                SearchScreen(navController)
            }
            composable(Routes.Notification.route) {
                NotificationScreen()
            }
            composable(Routes.Profile.route) {
                ProfileScreen(navController)
            }
            composable(Routes.Setting.route) {
                SettingScreen(navController)
            }

        }
    }
}

@Composable
fun CustomBottomNavBar(navController: NavHostController) {

    val backStackEntry = navController.currentBackStackEntryAsState()

    val bottomNavItems = listOf(
        BottomNavItem("Home", Routes.Home.route, Icons.Rounded.Home),
        BottomNavItem("Notification", Routes.Notification.route, Icons.Rounded.Notifications),
        BottomNavItem("Profile", Routes.Profile.route, Icons.Rounded.Person),
        BottomNavItem("Settings", Routes.Setting.route, Icons.Rounded.Settings)
    )

    BottomAppBar(containerColor = Color.Black, contentColor = MaterialTheme.colorScheme.primary) {
        bottomNavItems.forEach {
            val selected = it.route == backStackEntry.value?.destination?.route

            NavigationBarItem(selected = selected, onClick = {
                navController.navigate(it.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            }, icon = { Icon(imageVector = it.icon, contentDescription = it.title) })

        }
    }

}