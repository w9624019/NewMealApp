package uk.ac.tees.mad.w9624019.newmealapp.viewmodels.details

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import coil.compose.rememberAsyncImagePainter
import uk.ac.tees.mad.w9624019.newmealapp.R
import uk.ac.tees.mad.w9624019.newmealapp.model.response.Category

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MealDetailsScreen(meal: Category?) {

    // we have created enum for managing the states based on the transition
    var profilePictureState by remember { mutableStateOf(MealProfilePictureState.Normal) }
    val transition = updateTransition(targetState = profilePictureState, label = "")
    val imageSizeDp by transition.animateDp(targetValueByState = { it.size }, label = "")
    val color by transition.animateColor(targetValueByState = { it.color }, label = "")
    val widthSize by transition.animateDp(targetValueByState = { it.borderWidth }, label = "")

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

    Column {
        Row {
            Card (
                modifier = Modifier.padding(16.dp),
                shape = CircleShape,
                border = BorderStroke(
                    width = widthSize,
                    color = color
                )
            ){
                Image(
                    painter = rememberAsyncImagePainter(model = meal?.strCategoryThumb),
                    contentDescription = "thumb",
                    modifier = Modifier
                        .size(imageSizeDp)
                        .padding(10.dp)

                )
            }

            Text(
                text = meal?.strCategory ?: "default name",
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            )

        }

        Button(
            onClick = {
                profilePictureState = if(profilePictureState == MealProfilePictureState.Normal)
                    MealProfilePictureState.Expanded
                else
                    MealProfilePictureState.Normal
            }, modifier = Modifier

                .padding(10.dp)
        ) {
            Text("Change state of meal picture")
        }


    }

}
enum class MealProfilePictureState(val color: Color, val size: Dp, val borderWidth: Dp) {
    Normal(Color.Magenta, 120.dp, 8.dp),
    Expanded(Color.Green, 200.dp, 24.dp)
}