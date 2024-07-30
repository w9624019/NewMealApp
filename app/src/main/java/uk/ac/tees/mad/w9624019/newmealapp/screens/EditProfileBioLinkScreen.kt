package uk.ac.tees.mad.w9624019.newmealapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import uk.ac.tees.mad.w9624019.newmealapp.R
import uk.ac.tees.mad.w9624019.newmealapp.util.PreferenceHelper

@Composable
fun EditProfileBioLinkScreen(
    navHostController: NavHostController, isBio: Boolean = false, value: String
) {

    var editText by remember { mutableStateOf(if (isBio) value else value.replace("@", "/")) }

    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(onClick = {

                navHostController.popBackStack()
            }, modifier = Modifier) {
                Image(
                    painter = painterResource(id = R.drawable.ic_close_icon),
                    contentDescription = "close_icon",
                )
            }
            Text(
                text = "Edit ${if (isBio) "Bio" else "Link"}",
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium)
            )

            TextButton(onClick = {
                if (isBio) {
                    PreferenceHelper.setBioOrLinkData(context, "bio", editText)
                } else {
                    PreferenceHelper.setBioOrLinkData(context, "link", editText)
                }

                navHostController.popBackStack()
            }, modifier = Modifier) {
                Image(
                    painter = painterResource(id = R.drawable.ic_check_icon),
                    contentDescription = "close_icon",
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .then(
                    Modifier
                        .padding(horizontal = 10.dp)
                        .border(
                            width = 1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp)
                        )
                )
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (isBio) "Bio" else "Link", style = TextStyle(
                            fontSize = 15.sp, fontWeight = FontWeight.Normal, color = Color.Black
                        )
                    )
                    IconButton(onClick = {
                        editText = ""
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_close_icon),
                            contentDescription = "close_icon",
                        )
                    }

                }
                BasicTextField(
                    value = editText,
                    onValueChange = {
                        editText = it
                    },
                    textStyle = TextStyle(fontSize = 15.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                )
            }
        }
    }
}