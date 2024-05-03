package test.team.nti.foodies.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import test.team.nti.foodies.R
import test.team.nti.foodies.presentation.common.BottomButton
import test.team.nti.foodies.presentation.common.EmptyScreen
import test.team.nti.foodies.presentation.common.ItemsGrid
import test.team.nti.foodies.presentation.common.LoadingScreen
import test.team.nti.foodies.presentation.navigation.Route
import test.team.nti.foodies.presentation.search.components.SearchField

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel
) {

    val itemsGridState = rememberLazyGridState()

    LaunchedEffect(Unit) {
        viewModel.fetchItems()
        viewModel.setCartPrice()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Surface(
                shadowElevation = 9.dp,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    horizontalArrangement = Arrangement.spacedBy(32.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SearchField(
                        input = viewModel.searchString.value,
                        onInputChange = { viewModel.onInputChange(it) },
                        onGoBackButtonClick = { navController.popBackStack() },
                        onClearInputButtonClick = { viewModel.cleanSearchFiled() })
                }
            }
            if (viewModel.itemsOfSearch.isEmpty() && viewModel.searchString.value.isBlank()) {
                EmptyScreen(
                    message = stringResource(R.string.search_screen_initial)
                )
            } else if (viewModel.itemsOfSearch.isEmpty() && viewModel.searchString.value.isNotBlank()) {
                EmptyScreen(message = stringResource(R.string.search_screen_empty))
            } else if (viewModel.itemsOfSearch.isNotEmpty() && viewModel.searchString.value.isNotBlank()) {
                ItemsGrid(
                    items = viewModel.itemsOfSearch,
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
                contentDescription = stringResource(R.string.cart_button_icon)
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
