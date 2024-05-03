package test.team.nti.foodies.presentation.cart.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import test.team.nti.foodies.R
import test.team.nti.foodies.model.Product
import test.team.nti.foodies.presentation.common.QuantityChangeButton

@Composable
fun CartItem(
    item: Product,
    itemQuantity: Int,
    onAddToCartClick: (Int) -> Unit,
    onRemoveFromCart: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)

    ) {
        Image(
            painter = painterResource(id = R.drawable.photo),
            contentDescription = stringResource(id = R.string.item_image),
            modifier = Modifier.size(96.dp)
        )

        Column(
            modifier = Modifier.height(96.dp), verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black.copy(alpha = 0.87f)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    QuantityChangeButton(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        itemQuantity = itemQuantity,
                        onAddToCartClick = { onAddToCartClick(item.id) },
                        onRemoveFromCart = { onRemoveFromCart(item.id) })
                }
                Column {
                    Text(
                        text = stringResource(id = R.string.item_price,item.priceCurrent / 100),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black.copy(alpha = 0.87f)
                    )
                    if (item.priceOld != null) {
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = stringResource(id = R.string.item_price,item.priceOld / 100),
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.Black.copy(alpha = 0.6f),
                            textDecoration = TextDecoration.LineThrough
                        )
                    }
                }
            }
        }
    }
}
