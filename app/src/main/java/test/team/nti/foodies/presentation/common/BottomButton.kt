package test.team.nti.foodies.presentation.common

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import test.team.nti.foodies.ui.theme.OrangePrimary

@Composable
fun BoxScope.BottomButton(cartPrice: Int, onButtonClick:()->Unit, content: @Composable () -> Unit) {

    val bottomBarOffset: State<Dp> = animateDpAsState(
        targetValue = if (cartPrice > 0) 0.dp else 70.dp,
        animationSpec = tween(durationMillis = 150, easing = LinearEasing),
        label = "cartButtonAnimation"
    )

    Row(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .offset(y = bottomBarOffset.value)
            .fillMaxWidth()
            .shadow(elevation = 25.dp)
            .background(color = Color.White)

    ) {
        Button(
            onClick = { onButtonClick() },
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
        ) {
            content()
        }
    }
}