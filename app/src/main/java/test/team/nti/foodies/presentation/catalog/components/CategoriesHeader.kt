package test.team.nti.foodies.presentation.catalog.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import test.team.nti.foodies.model.Category
import test.team.nti.foodies.ui.theme.OrangePrimary

@Composable
fun CategoriesHeader(
    categories: List<Category>,
    selectedCategory: String,
    onSelectChange: (Category) -> Unit,
    categoriesListState: LazyListState,
    itemsGridState: LazyGridState
) {
    val coroutineScope = rememberCoroutineScope()

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
                    onSelectChange(item)
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
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (item.name == selectedCategory)
                        OrangePrimary
                    else
                        Color.White
                ),
                contentPadding = PaddingValues(
                    horizontal = 16.dp, vertical = 0.dp
                )
            ) {
                Text(
                    text = item.name,
                    modifier = Modifier.padding(0.dp),
                    style = MaterialTheme.typography.titleMedium,
                    color = if (item.name == selectedCategory) Color.White else Color.Black.copy(
                        alpha = 0.87f
                    )
                )
            }
        }
        item { Spacer(modifier = Modifier.width(16.dp)) }
    }
}