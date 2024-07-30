package uk.ac.tees.mad.w9624019.newmealapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.TextButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import uk.ac.tees.mad.w9624019.newmealapp.navigation.Routes
import uk.ac.tees.mad.w9624019.newmealapp.util.Constant
import uk.ac.tees.mad.w9624019.newmealapp.viewmodels.AuthViewModel

@Composable
fun SettingScreen(controller: NavHostController) {

    val authViewModel = AuthViewModel()
    Column(modifier = Constant.widthModifier) {

        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(modifier = Constant.widthModifier) {
            item {
                TextButton(onClick = {

                }) {
                    Text(
                        text = "Log out from the application", style = TextStyle(
                            fontSize = 20.sp, fontWeight = FontWeight.Normal, color = Color.Red
                        ), modifier = Constant.widthModifier
                            .padding(
                                horizontal = 10.dp, vertical = 5.dp
                            )
                            .clickable {
                                authViewModel.logout()
                                controller.navigate(Routes.Login.route)
                            }
                    )
                }

            }
        }
    }

}