package test.team.nti.foodies.presentation.cart.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import test.team.nti.foodies.R
import test.team.nti.foodies.model.Product
import test.team.nti.foodies.ui.theme.OrangePrimary

@Composable
fun CartItem(item: Product) {
    Row(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.photo),
            contentDescription = "Cart item photo",
            modifier = Modifier.size(96.dp)
        )

        Column(
            modifier = Modifier.height(96.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = item.name,style = MaterialTheme.typography.bodySmall,
                color = Color.Black.copy(alpha = 0.87f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ElevatedButton(
                        onClick = { /*TODO*/ },
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
                            contentDescription = "Remove item",
                            tint = OrangePrimary
                        )
                    }
                    Text(
                        text = "100",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black.copy(alpha = 0.87f)
                    )
                    ElevatedButton(
                        onClick = { /*TODO*/ },
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
                            contentDescription = "Add item",
                            tint = OrangePrimary
                        )
                    }
                }
                Column {
                    Text(
                        text = "${item.priceCurrent} ₽",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black.copy(alpha = 0.87f)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${item.priceOld} ₽",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Black.copy(alpha = 0.6f),
                        textDecoration = TextDecoration.LineThrough
                    )
                }
            }
        }

    }
}
