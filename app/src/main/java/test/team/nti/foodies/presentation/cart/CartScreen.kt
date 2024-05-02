package test.team.nti.foodies.presentation.cart

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import test.team.nti.foodies.R
import test.team.nti.foodies.presentation.cart.components.CartItem
import test.team.nti.foodies.ui.theme.OrangePrimary

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CartScreen(navController: NavController, viewModel: CartViewModel) {

    val bottomBarOffset: State<Dp> =
        animateDpAsState(
            targetValue = if (viewModel.cartPrice.value > 0) 0.dp else 70.dp,
            animationSpec = tween(durationMillis = 250, easing = LinearEasing),
            label = "cartButtonAnimation"
        )

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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .zIndex(10f)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { navController.popBackStack() }, modifier = Modifier
                        .padding(0.dp)
                        .size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_left_icon),
                        contentDescription = "Go back button",
                        tint = OrangePrimary
                    )
                }
                Text(
                    text = "Корзина", style = MaterialTheme.typography.headlineMedium,
                    color = Color.Black.copy(alpha = 0.87f)
                )
            }
        }

        if (viewModel.cartPrice.value == 0) {
            Text(
                text = "Пусто, выберите блюда\n" +
                        "в каталоге :)",
                modifier = Modifier.padding(0.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black.copy(alpha = 0.6f),
                textAlign = TextAlign.Center
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

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .offset(y = bottomBarOffset.value)
                .shadow(elevation = 25.dp)
                .background(color = Color.White)
        ) {
            Button(
                onClick = { },
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
            ) {
                Text(
                    text = "Заказать за ",
                    modifier = Modifier.padding(0.dp),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
                Text(
                    text = "${viewModel.cartPrice.value / 100} ₽",
                    modifier = Modifier.padding(0.dp),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
            }
        }

        AnimatedVisibility(
            visible = viewModel.isLoading.value,
            exit = fadeOut(animationSpec = tween(600))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(64.dp),
                    color = OrangePrimary,
                    strokeWidth = 4.dp
                )
            }
        }
    }
}
