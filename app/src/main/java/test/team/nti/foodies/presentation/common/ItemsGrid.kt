package test.team.nti.foodies.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import test.team.nti.foodies.model.Product
import test.team.nti.foodies.presentation.common.catalog_item.CatalogItem

@Composable
fun ItemsGrid(
    items: List<Product>,
    itemsGridState: LazyGridState,
    navController: NavController,
    onAddItemClick: (Int) -> Unit,
    onRemoveItemClick: (Int) -> Unit,
    getItemQuantity: (Int) -> Int
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
        state = itemsGridState,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        items(items = items) { item ->
            CatalogItem(
                navController = navController,
                item = item,
                onAddToCartClick = { itemId -> onAddItemClick(itemId) },
                onRemoveFromCart = { itemId -> onRemoveItemClick(itemId) },
                itemQuantity = getItemQuantity(item.id)
            )
        }
        items((items.size % 2) + 1) { _ ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp)
            )
        }
    }
}