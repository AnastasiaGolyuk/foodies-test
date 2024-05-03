package test.team.nti.foodies.presentation.common.catalog_item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import test.team.nti.foodies.R
import test.team.nti.foodies.model.Product
import test.team.nti.foodies.presentation.common.QuantityChangeButton
import test.team.nti.foodies.presentation.navigation.Route
import test.team.nti.foodies.ui.theme.GreyBg

@Composable
fun CatalogItem(
    navController: NavController,
    item: Product,
    onAddToCartClick: (Int) -> Unit,
    onRemoveFromCart: (Int) -> Unit,
    itemQuantity: Int
) {
    Box(
        modifier = Modifier
            .background(color = GreyBg, shape = RoundedCornerShape(8.dp))
            .fillMaxSize()
            .clickable { navController.navigate("${Route.ItemCardScreen.route}/${item.id}") }
    ) {
        ItemTagsRow(item = item)
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.photo),
                contentDescription = stringResource(id = R.string.item_image)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 12.dp)
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black.copy(alpha = 0.87f),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${item.measure} ${item.measureUnit}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black.copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.height(12.dp))
                if (itemQuantity == 0) {
                    DefaultItemButton(item = item, onAddToCartClick = { onAddToCartClick(it) })
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        QuantityChangeButton(
                            itemQuantity = itemQuantity,
                            onAddToCartClick = { onAddToCartClick(item.id) },
                            onRemoveFromCart = { onRemoveFromCart(item.id) })
                    }
                }
            }
        }
    }
}
