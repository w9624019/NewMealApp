package uk.ac.tees.mad.w9624019.newmealapp.screens

import androidx.compose.foundation.Image
import uk.ac.tees.mad.w9624019.newmealapp.widgets.AuthBottomLabel
import uk.ac.tees.mad.w9624019.newmealapp.widgets.CustomButton
import uk.ac.tees.mad.w9624019.newmealapp.widgets.CustomTextFiled
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import uk.ac.tees.mad.w9624019.newmealapp.R
import uk.ac.tees.mad.w9624019.newmealapp.navigation.Routes
import uk.ac.tees.mad.w9624019.newmealapp.util.Util
import uk.ac.tees.mad.w9624019.newmealapp.validation.Validation
import uk.ac.tees.mad.w9624019.newmealapp.viewmodels.AuthViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(navHostController: NavHostController) {

    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    val focusRequester = remember { FocusRequester() }
    val authViewModel = AuthViewModel()
    val firebaseUser by authViewModel.firebaseUser.observeAsState()
    val message by authViewModel.errorMessage.observeAsState()

    val context = LocalContext.current

    //navigation
    LaunchedEffect(firebaseUser) {
        if (firebaseUser != null) {
            navHostController.navigate(Routes.BottomNav.route) {
                popUpTo(navHostController.graph.findStartDestination().id) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
    }


    LaunchedEffect(message) {
        if (message != null) {
            Util.showToast(context, message!!)
        }
    }

    val modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp, 0.dp)

    Box(modifier = Modifier.background(color = Color.White)) {
        val constraints = ConstraintSet {
            val imageRef = createRefFor("image")
            val text = createRefFor("text")
            val topGuidLine = createGuidelineFromTop(0.1f)
            val bottomGuidLine = createGuidelineFromBottom(0.1f)

            constrain(imageRef) {
                top.linkTo(topGuidLine)
                bottom.linkTo(bottomGuidLine)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }

            constrain(text) {
                top.linkTo(imageRef.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }

        }
        ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "app_logo",
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .layoutId("image"),
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Welcome to NewMealApp,", style = TextStyle(
                    color = Color.Blue,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    textAlign = TextAlign.Start
                ), modifier = modifier
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomTextFiled(textFiled = email, callBack = {
                email = it
            }, "Email", modifier, false)

            Spacer(modifier = Modifier.height(20.dp))

            CustomTextFiled(textFiled = password, callBack = {
                password = it
            }, "Password", modifier, true, focusRequester)

            Spacer(modifier = Modifier.height(20.dp))

            CustomButton({
                val filedList = arrayListOf(email, password)
                if (Validation.isFormDataValid(filedList)) {
                    authViewModel.login(email.text.trim(), password.text.trim())
                } else {
                    Util.showToast(context = context, message = "Please fill up all values")
                }
            }, "Login", modifier)

            Spacer(modifier = Modifier.height(20.dp))

            AuthBottomLabel("Don't have an account? ", "Register", modifier = Modifier.clickable {
                navHostController.navigate(Routes.Register.route) {
                    popUpTo(navHostController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            })
        }
    }

}

