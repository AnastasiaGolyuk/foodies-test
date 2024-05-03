package test.team.nti.foodies.presentation.search

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import test.team.nti.foodies.R
import test.team.nti.foodies.presentation.catalog.components.CatalogItem
import test.team.nti.foodies.presentation.catalog.components.EmptyCatalogScreen
import test.team.nti.foodies.presentation.common.LoadingScreen
import test.team.nti.foodies.presentation.navigation.Route
import test.team.nti.foodies.ui.theme.OrangePrimary

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel
) {

    val itemsGridState = rememberLazyGridState()

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    val bottomBarOffset: State<Dp> = animateDpAsState(
        targetValue = if (viewModel.cartPrice.value > 0) 0.dp else 70.dp,
        animationSpec = tween(durationMillis = 150, easing = LinearEasing),
        label = "cartButtonAnimation"
    )

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
                    .padding(6.dp),
                horizontalArrangement = Arrangement.spacedBy(32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                TextField(
                    value = viewModel.searchString.value,
                    onValueChange = {
                        viewModel.onInputChange(it)
                        println("aaaaaaaa ${viewModel.itemsOfSearch.size} ${viewModel.searchString.value.isBlank()}")
                    },
                    placeholder = {
                        Text(
                            text = "Найти блюдо", style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black.copy(alpha = 0.6f)
                        )
                    },
                    leadingIcon = {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            }, modifier = Modifier
                                .padding(0.dp)
                                .size(24.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_left_icon),
                                contentDescription = "Go back button",
                                tint = OrangePrimary
                            )
                        }
                    },
                    trailingIcon = {
                        if (viewModel.searchString.value.isNotBlank()) {
                            IconButton(
                                onClick = {
                                    viewModel.cleanSearchFiled()
                                }, modifier = Modifier
                                    .padding(0.dp)
                                    .size(24.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.cancel_icon),
                                    contentDescription = "Clear search string",
                                    tint = Color.Black.copy(alpha = 0.6f)
                                )
                            }
                        }
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                        }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        errorContainerColor = Color.White,
                        unfocusedPlaceholderColor = Color.Black.copy(alpha = 0.6f),
                        focusedPlaceholderColor = Color.Black.copy(alpha = 0.6f),
                        unfocusedTextColor = Color.Black.copy(alpha = 0.87f),
                        focusedTextColor = Color.Black.copy(alpha = 0.87f),
                    ),
                    textStyle = MaterialTheme.typography.bodyMedium,
                    shape = RoundedCornerShape(10.dp),
                )
            }
        }
        if (viewModel.itemsOfSearch.isEmpty() && viewModel.searchString.value.isBlank()) {
            EmptyCatalogScreen(
                message = "Введите название блюда, \n" +
                        "которое ищете"
            )
        } else if (viewModel.itemsOfSearch.isEmpty() && viewModel.searchString.value.isNotBlank()) {
            EmptyCatalogScreen(message = "Ничего не нашлось :(")
        } else if (viewModel.itemsOfSearch.isNotEmpty() && viewModel.searchString.value.isNotBlank()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(start = 16.dp, end = 16.dp, top = 80.dp),
                state = itemsGridState,
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
            ) {
                items(items = viewModel.itemsOfSearch) { item ->
                    CatalogItem(
                        navController = navController,
                        item = item,
                        onAddToCartClick = { itemId -> viewModel.addItemToCart(itemId) },
                        onRemoveFromCart = { itemId -> viewModel.removeItemFromCart(itemId) },
                        itemQuantity = viewModel.getItemQuantity(itemId = item.id)
                    )
                }
                items((viewModel.itemsOfSearch.size % 2) + 1) { _ ->
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
                .offset(y = bottomBarOffset.value)
                .fillMaxWidth()
                .shadow(elevation = 25.dp)
                .background(color = Color.White)

        ) {
            Button(
                onClick = { navController.navigate(Route.CartScreen.route) },
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.cart_icon),
                    contentDescription = "Cart button icon"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${viewModel.cartPrice.value / 100} ₽",
                    modifier = Modifier.padding(0.dp),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
            }
        }
        LoadingScreen(isLoading = viewModel.isLoading.value)
    }
}
