package test.team.nti.foodies.presentation.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import test.team.nti.foodies.R
import test.team.nti.foodies.presentation.catalog.components.CatalogHeader
import test.team.nti.foodies.presentation.catalog.components.CategoriesHeader
import test.team.nti.foodies.presentation.catalog.components.FilterModalDialog
import test.team.nti.foodies.presentation.common.BottomButton
import test.team.nti.foodies.presentation.common.EmptyScreen
import test.team.nti.foodies.presentation.common.ItemsGrid
import test.team.nti.foodies.presentation.common.LoadingScreen
import test.team.nti.foodies.presentation.navigation.Route

@Composable
fun CatalogScreen(navController: NavController, viewModel: CatalogViewModel) {

    var items = viewModel.itemsOfCategory

    val itemsGridState = rememberLazyGridState()
    val categoriesListState = rememberLazyListState()

    var showSheet by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (viewModel.selectedCategory.value.isEmpty()) {
            viewModel.fetchCategories()
            viewModel.fetchTags()
            viewModel.fetchItems()
        }
        viewModel.setCartPrice()
    }

    if (showSheet) {
        FilterModalDialog(
            selectedFilters = viewModel.selectedTags,
            onDismiss = {
                showSheet = it
            },
            onDoneClick = { tags, idDismissed ->
                showSheet = idDismissed
                viewModel.setFilters(tags)
                viewModel.filterTagsItem()
            })
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Surface(
                shadowElevation = 9.dp,
            ) {
                Column {
                    CatalogHeader(
                        selectedTags = viewModel.selectedTags,
                        onFilterButtonClick = { showSheet = true },
                        onSearchButtonClick = { navController.navigate(Route.SearchScreen.route) })

                    CategoriesHeader(
                        categories = viewModel.categories,
                        selectedCategory = viewModel.selectedCategory.value,
                        onSelectChange = { viewModel.setCategory(it) },
                        categoriesListState = categoriesListState,
                        itemsGridState = itemsGridState
                    )
                }
            }
            if (viewModel.itemsOfCategory.isEmpty()) {
                EmptyScreen(message = stringResource(R.string.catalog_empty_category))
            } else {
                if (viewModel.selectedTags.isNotEmpty()) {
                    if (viewModel.itemsWithFilters.isEmpty()) {
                        EmptyScreen(
                            message = stringResource(R.string.catalog_empty_filters)
                        )
                    } else {
                        items = viewModel.itemsWithFilters
                    }
                }
                ItemsGrid(
                    items = items,
                    itemsGridState = itemsGridState,
                    navController = navController,
                    onAddItemClick = { viewModel.addItemToCart(it) },
                    onRemoveItemClick = { viewModel.removeItemFromCart(it) },
                    getItemQuantity = { viewModel.getItemQuantity(itemId = it) }
                )
            }
        }

        BottomButton(
            cartPrice = viewModel.cartPrice.value,
            onButtonClick = { navController.navigate(Route.CartScreen.route) }) {
            Icon(
                painter = painterResource(id = R.drawable.cart_icon),
                contentDescription = stringResource(id = R.string.cart_button_icon)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.item_price, viewModel.cartPrice.value / 100),
                modifier = Modifier.padding(0.dp),
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }

        LoadingScreen(isLoading = viewModel.isLoading.value)
    }
}
