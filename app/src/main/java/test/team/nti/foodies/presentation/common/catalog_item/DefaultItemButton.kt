package test.team.nti.foodies.presentation.common.catalog_item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import test.team.nti.foodies.R
import test.team.nti.foodies.model.Product

@Composable
fun DefaultItemButton(item:Product, onAddToCartClick:(Int)->Unit) {
    ElevatedButton(
        onClick = { onAddToCartClick(item.id) },
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        shape = RoundedCornerShape(size = 8.dp),
        colors = ButtonDefaults.elevatedButtonColors(containerColor = Color.White),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 4.dp),
        border = BorderStroke(width = 0.dp, color = Color.Transparent)
    ) {
        Text(
            text = stringResource(R.string.item_price, item.priceCurrent / 100),
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black.copy(alpha = 0.87f)
        )
        if (item.priceOld != null) {
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(R.string.item_price, item.priceOld / 100),
                style = MaterialTheme.typography.titleSmall,
                color = Color.Black.copy(alpha = 0.6f),
                textDecoration = TextDecoration.LineThrough,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}