package test.team.nti.foodies.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import test.team.nti.foodies.R
import test.team.nti.foodies.ui.theme.OrangePrimary

@Composable
fun RowScope.QuantityChangeButton(modifier: Modifier = Modifier ,itemQuantity:Int, onAddToCartClick:()->Unit,onRemoveFromCart:()->Unit) {
    ElevatedButton(
        onClick = { onRemoveFromCart() },
        modifier = Modifier
            .padding(0.dp)
            .size(40.dp),
        shape = RoundedCornerShape(size = 8.dp),
        colors = ButtonDefaults.elevatedButtonColors(containerColor = Color.White),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 4.dp),
        border = BorderStroke(width = 0.dp, color = Color.Transparent),
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.minus_icon),
            contentDescription = stringResource(R.string.remove_item),
            tint = OrangePrimary
        )
    }
    Text(
        text = "$itemQuantity",
        modifier = modifier,
        style = MaterialTheme.typography.titleMedium,
        color = Color.Black.copy(alpha = 0.87f)
    )
    ElevatedButton(
        onClick = { onAddToCartClick() },
        modifier = Modifier
            .padding(0.dp)
            .size(40.dp),
        shape = RoundedCornerShape(size = 8.dp),
        colors = ButtonDefaults.elevatedButtonColors(containerColor = Color.White),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 4.dp),
        border = BorderStroke(width = 0.dp, color = Color.Transparent),
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.plus_icon),
            contentDescription = stringResource(R.string.add_item),
            tint = OrangePrimary
        )
    }
}