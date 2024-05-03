package test.team.nti.foodies.presentation.cart

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import test.team.nti.foodies.R
import test.team.nti.foodies.presentation.cart.components.CartHeader
import test.team.nti.foodies.presentation.cart.components.CartItem
import test.team.nti.foodies.presentation.common.BottomButton
import test.team.nti.foodies.presentation.common.EmptyScreen
import test.team.nti.foodies.presentation.common.LoadingScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CartScreen(navController: NavController, viewModel: CartViewModel) {

    LaunchedEffect(Unit) {
        viewModel.fetchItems()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            shadowElevation = 9.dp,
        ) {
            CartHeader(onBackButtonClick = {navController.popBackStack()})
        }
        if (viewModel.cartPrice.value == 0) {
            EmptyScreen(
                message = stringResource(R.string.cart_empty_screen)
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 65.dp)
            ) {
                items(items = viewModel.itemsInCart, key = { it.id }) { item ->
                    Column(modifier = Modifier.animateItemPlacement()) {
                        CartItem(
                            item = item,
                            itemQuantity = viewModel.getItemQuantity(itemId = item.id),
                            onAddToCartClick = { id -> viewModel.addItemToCart(id) },
                            onRemoveFromCart = { id -> viewModel.removeItemFromCart(id) })
                        Divider(color = Color.Black.copy(alpha = 0.12f), thickness = 1.dp)
                    }
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(75.dp)
                    )
                }
            }
        }
        BottomButton(
            cartPrice = viewModel.cartPrice.value,
            onButtonClick = { /*TODO: Оплата корзины*/ }) {
            Text(
                text = stringResource(R.string.cart_order_button),
                modifier = Modifier.padding(0.dp),
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
            Text(
                text = stringResource(id = R.string.item_price,viewModel.cartPrice.value / 100),
                modifier = Modifier.padding(0.dp),
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }
        LoadingScreen(isLoading = viewModel.isLoading.value)
    }
}
