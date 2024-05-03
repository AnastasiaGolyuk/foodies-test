package test.team.nti.foodies.presentation.catalog.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import test.team.nti.foodies.R
import test.team.nti.foodies.data.entity.TagInApp
import test.team.nti.foodies.model.Product
import test.team.nti.foodies.presentation.navigation.Route
import test.team.nti.foodies.ui.theme.GreyBg
import test.team.nti.foodies.ui.theme.OrangePrimary

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
        Row {
            if (item.priceOld != null) {
                Image(
                    painter = painterResource(id = R.drawable.sale_tag),
                    contentDescription = "Item image",
                    modifier = Modifier
                        .padding(top = 8.dp, start = 8.dp)
                        .size(24.dp)
                )
            }
            for (tag in item.tagIds) {
                val iconId = getTagIconId(tag)
                Image(
                    painter = painterResource(id = iconId),
                    contentDescription = "Item image",
                    modifier = Modifier
                        .padding(top = 8.dp, start = 8.dp)
                        .size(24.dp)
                )
            }
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.photo),
                contentDescription = "Item image"
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
                            text = "${item.priceCurrent / 100} ₽",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Black.copy(alpha = 0.87f)
                        )
                        if (item.priceOld != null) {
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${item.priceOld / 100} ₽",
                                style = MaterialTheme.typography.titleSmall,
                                color = Color.Black.copy(alpha = 0.6f),
                                textDecoration = TextDecoration.LineThrough,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                        }
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ElevatedButton(
                            onClick = { onRemoveFromCart(item.id) },
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
                            text = "$itemQuantity",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Black.copy(alpha = 0.87f)
                        )
                        ElevatedButton(
                            onClick = { onAddToCartClick(item.id) },
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
                }
            }
        }
    }
}

fun getTagIconId(tagId: Int): Int {
    return TagInApp.getIconId(tagId)
}