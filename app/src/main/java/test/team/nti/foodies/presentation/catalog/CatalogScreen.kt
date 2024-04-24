package test.team.nti.foodies.presentation.catalog

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import test.team.nti.foodies.R
import test.team.nti.foodies.presentation.catalog.components.CatalogItem
import test.team.nti.foodies.ui.theme.FoodiesTheme
import test.team.nti.foodies.ui.theme.OrangePrimary

@Composable
fun CatalogScreen(navController : NavController, viewModel: CatalogViewModel) {
    val categories = viewModel.categories
    val items = viewModel.items
    val selectedCategory = viewModel.selectedCategory
//    val items = List(7) { index -> }

    LaunchedEffect(Unit) {
        viewModel.fetchCategories()
        viewModel.fetchItems()
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Surface(
            shadowElevation = 9.dp,
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 8.dp, end = 8.dp, top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.filter_icon),
                            contentDescription = "Filter button"
                        )
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
                            .padding(vertical = 8.dp)
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        item { Spacer(modifier = Modifier.width(16.dp)) }
                        items(items = categories) { item ->
                            Button(
                                onClick = { viewModel.setCategory(item.name) },
                                modifier = Modifier.padding(0.dp),
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = if (item.name == selectedCategory.value) OrangePrimary else Color.White),
                                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
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
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ) {
            items(items = items) { item ->
                CatalogItem(navController = navController, item = item)
            }
        }
    }
}

//@Preview
//@Composable
//fun CatPrev() {
//    FoodiesTheme {
//        CatalogScreen()
//    }
//}