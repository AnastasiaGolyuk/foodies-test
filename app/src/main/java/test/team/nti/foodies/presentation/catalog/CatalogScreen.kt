package test.team.nti.foodies.presentation.catalog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import test.team.nti.foodies.R
import test.team.nti.foodies.presentation.catalog.components.CatalogItem
import test.team.nti.foodies.presentation.catalog.components.EmptyCatalogScreen
import test.team.nti.foodies.presentation.catalog.components.FilterModalDialog
import test.team.nti.foodies.presentation.navigation.Route
import test.team.nti.foodies.ui.theme.OrangePrimary

@Composable
fun CatalogScreen(navController: NavController, viewModel: CatalogViewModel) {

    val categories = viewModel.categories
    var items = viewModel.itemsOfCategory
    val selectedCategory = viewModel.selectedCategory

    val itemsGridState = rememberLazyGridState()
    val categoriesListState = rememberLazyListState()

    val coroutineScope = rememberCoroutineScope()

    val bottomBarOffset: State<Dp> = animateDpAsState(
        targetValue = if (viewModel.cartPrice.value > 0) 0.dp else 70.dp,
        animationSpec = tween(durationMillis = 150, easing = LinearEasing),
        label = "cartButtonAnimation"
    )

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
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White)
                            .wrapContentHeight()
                            .padding(start = 8.dp, end = 8.dp, top = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box {
                            IconButton(onClick = { showSheet = true }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.filter_icon),
                                    contentDescription = "Filter button"
                                )
                            }
                            if (viewModel.selectedTags.isNotEmpty()) {
                                Box(
                                    modifier = Modifier
                                        .size(17.dp)
                                        .background(
                                            color = OrangePrimary,
                                            shape = RoundedCornerShape(100.dp)
                                        )
                                        .align(Alignment.TopEnd),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "${viewModel.selectedTags.size}",
                                        modifier = Modifier.padding(0.dp),
                                        style = MaterialTheme.typography.titleLarge,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "App logo"
                        )
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.search_icon),
                                contentDescription = "Search button"
                            )
                        }
                    }

                    LazyRow(
                        modifier = Modifier
                            .background(color = Color.White)
                            .padding(vertical = 8.dp)
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        state = categoriesListState,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        item { Spacer(modifier = Modifier.width(16.dp)) }
                        items(items = categories) { item ->
                            Button(
                                onClick = {
                                    viewModel.setCategory(item)
                                    coroutineScope.launch {
                                        itemsGridState.animateScrollToItem(0)
                                        categoriesListState.animateScrollToItem(
                                            index = categories.indexOf(item) + 1,
                                            scrollOffset = -100
                                        )
                                    }
                                },
                                modifier = Modifier.padding(0.dp),
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = if (item.name == selectedCategory.value) OrangePrimary else Color.White),
                                contentPadding = PaddingValues(
                                    horizontal = 16.dp, vertical = 0.dp
                                )
                            ) {
                                Text(
                                    text = item.name,
                                    modifier = Modifier.padding(0.dp),
                                    style = MaterialTheme.typography.titleMedium,
                                    color = if (item.name == selectedCategory.value) Color.White else Color.Black.copy(
                                        alpha = 0.87f
                                    )
                                )
                            }
                        }
                        item { Spacer(modifier = Modifier.width(16.dp)) }
                    }
                }
            }
            if (viewModel.itemsOfCategory.isEmpty()) {
                EmptyCatalogScreen(message = "Нет блюд, соответсвующих\n" + "данной категории :(")
            } else {
                if (viewModel.selectedTags.isNotEmpty()) {
                    if (viewModel.itemsWithFilters.isEmpty()) {
                        EmptyCatalogScreen(
                            message = "Таких блюд в данной категории нет :(\n" +
                                    "Попробуйте изменить фильтры"
                        )
                    } else {
                        items = viewModel.itemsWithFilters
                    }
                }
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
                            onAddToCartClick = { itemId -> viewModel.addItemToCart(itemId) },
                            onRemoveFromCart = { itemId -> viewModel.removeItemFromCart(itemId) },
                            itemQuantity = viewModel.getItemQuantity(itemId = item.id)
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



        AnimatedVisibility(
            visible = viewModel.isLoading.value, exit = fadeOut(animationSpec = tween(600))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(64.dp), color = OrangePrimary, strokeWidth = 4.dp
                )
            }
        }
    }
}
