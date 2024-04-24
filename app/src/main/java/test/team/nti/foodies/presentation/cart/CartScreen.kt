package test.team.nti.foodies.presentation.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import test.team.nti.foodies.R
import test.team.nti.foodies.presentation.cart.components.CartItem
import test.team.nti.foodies.ui.theme.FoodiesTheme
import test.team.nti.foodies.ui.theme.OrangePrimary

@Composable
fun CartScreen(navController : NavController) {
    val itemsList = List(5) { index -> }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)){
    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Surface(
            shadowElevation = 9.dp, // play with the elevation values
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth().zIndex(10f)
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
        LazyColumn(
            modifier = Modifier.padding(top = 5.dp)
        ) {
            items(items = itemsList) {
                CartItem()
                Divider(color = Color.Black.copy(alpha = 0.12f), thickness = 1.dp)
            }
        }
    }
        Surface( modifier = Modifier
            .align(Alignment.BottomCenter),
            shadowElevation = 15.dp,
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
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
                        text = "2 160 ₽",
                        modifier = Modifier.padding(0.dp),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun CartPrev() {
//    FoodiesTheme {
//        CartScreen()
//    }
//}