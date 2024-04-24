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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import test.team.nti.foodies.R
import test.team.nti.foodies.model.Product
import test.team.nti.foodies.presentation.navigation.Route
import test.team.nti.foodies.ui.theme.GreyBg
import test.team.nti.foodies.ui.theme.OrangePrimary

@Composable
fun CatalogItem(navController: NavController, item: Product) {
    Box(
        modifier = Modifier
            .background(color = GreyBg, shape = RoundedCornerShape(8.dp))
            .fillMaxSize()
            .clickable { navController.navigate(Route.ItemCardScreen.route) }
    ) {
        for (tag in item.tag_ids){
            Image(
                painter = painterResource(id = R.drawable.sale_tag),
                contentDescription = "Item image",
                modifier = Modifier
                    .padding(8.dp)
                    .size(24.dp)
            )
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
                    text = "${item.measure} ${item.measure_unit}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black.copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.height(12.dp))
//                ElevatedButton(
//                    onClick = { /*TODO*/ },
//                    modifier = Modifier.fillMaxWidth().height(40.dp),
//                    shape = RoundedCornerShape(size = 8.dp),
//                    colors = ButtonDefaults.elevatedButtonColors(containerColor = Color.White),
//                    elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 4.dp),
//                    border = BorderStroke(width = 0.dp, color = Color.Transparent)
//                ) {
//                    Text(text = "480 ₽", style = MaterialTheme.typography.titleMedium, color = Color.Black.copy(alpha = 0.87f))
//                }
                ElevatedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    shape = RoundedCornerShape(size = 8.dp),
                    colors = ButtonDefaults.elevatedButtonColors(containerColor = Color.White),
                    elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 4.dp),
                    border = BorderStroke(width = 0.dp, color = Color.Transparent)
                ) {
                    Text(
                        text = "${item.price_current / 100} ₽",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black.copy(alpha = 0.87f)
                    )
                    if (item.price_old != null) {
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${item.price_old / 100} ₽",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.Black.copy(alpha = 0.6f),
                            textDecoration = TextDecoration.LineThrough
                        )
                    }
                }
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    ElevatedButton(
//                        onClick = { /*TODO*/ },
//                        modifier = Modifier
//                            .padding(0.dp).size(40.dp),
//                        shape = RoundedCornerShape(size = 8.dp),
//                        colors = ButtonDefaults.elevatedButtonColors(containerColor = Color.White),
//                        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 4.dp),
//                        border = BorderStroke(width = 0.dp, color = Color.Transparent),
//                        contentPadding = PaddingValues(0.dp)
//                    ) {
//                        Icon(painter = painterResource(id = R.drawable.minus_icon), contentDescription = "Remove item", tint = OrangePrimary)
//                    }
//                    Text(
//                        text = "100",
//                        style = MaterialTheme.typography.titleMedium,
//                        color = Color.Black.copy(alpha = 0.87f)
//                    )
//                    ElevatedButton(
//                        onClick = { /*TODO*/ },
//                        modifier = Modifier
//                            .padding(0.dp).size(40.dp),
//                        shape = RoundedCornerShape(size = 8.dp),
//                        colors = ButtonDefaults.elevatedButtonColors(containerColor = Color.White),
//                        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 4.dp),
//                        border = BorderStroke(width = 0.dp, color = Color.Transparent),
//                        contentPadding = PaddingValues(0.dp)
//                    ) {
//                        Icon(painter = painterResource(id = R.drawable.plus_icon), contentDescription = "Add item", tint = OrangePrimary)
//                    }
//                }
            }
        }
    }
}

//@Preview
//@Composable
//fun ItemPreview() {
//    val items = List(7) { index -> }
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(color = Color.White)
//    ) {
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(2),
//            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
//            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
//        ) {
//            items(items = items) { item ->
//                CatalogItem()
//            }
//        }
//    }
//}