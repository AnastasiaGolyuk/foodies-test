package test.team.nti.foodies.presentation.item_card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import test.team.nti.foodies.R
import test.team.nti.foodies.ui.theme.FoodiesTheme
import test.team.nti.foodies.ui.theme.OrangePrimary

@Composable
fun ItemCardScreen(navController : NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState()), verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.photo),
                contentDescription = "Item image",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Том ям",
                    modifier = Modifier.padding(0.dp),
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Black.copy(
                        alpha = 0.87f
                    )
                )
                Text(
                    text = "Кокосовое молоко, кальмары, креветки, помидоры черри, грибы вешанки",
                    modifier = Modifier.padding(0.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black.copy(
                        alpha = 0.6f
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize(),

                ) {
                Divider(
                    thickness = 1.dp, color = Color.Black.copy(
                        alpha = 0.12f
                    )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 13.dp, horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "Вес",
                        modifier = Modifier.padding(0.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black.copy(
                            alpha = 0.6f
                        )
                    )
                    Text(
                        text = "400 г",
                        modifier = Modifier.padding(0.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black.copy(
                            alpha = 0.87f
                        )
                    )
                }
                Divider(
                    thickness = 1.dp, color = Color.Black.copy(
                        alpha = 0.12f
                    )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 13.dp, horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Энерг. ценность",
                        modifier = Modifier.padding(0.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black.copy(
                            alpha = 0.6f
                        )
                    )
                    Text(
                        text = "198,9 ккал",
                        modifier = Modifier.padding(0.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black.copy(
                            alpha = 0.87f
                        )
                    )
                }
                Divider(
                    thickness = 1.dp, color = Color.Black.copy(
                        alpha = 0.12f
                    )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 13.dp, horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Белки",
                        modifier = Modifier.padding(0.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black.copy(
                            alpha = 0.6f
                        )
                    )
                    Text(
                        text = "10 г",
                        modifier = Modifier.padding(0.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black.copy(
                            alpha = 0.87f
                        )
                    )
                }
                Divider(
                    thickness = 1.dp, color = Color.Black.copy(
                        alpha = 0.12f
                    )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 13.dp, horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Жиры",
                        modifier = Modifier.padding(0.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black.copy(
                            alpha = 0.6f
                        )
                    )
                    Text(
                        text = "8,5 г",
                        modifier = Modifier.padding(0.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black.copy(
                            alpha = 0.87f
                        )
                    )
                }
                Divider(
                    thickness = 1.dp, color = Color.Black.copy(
                        alpha = 0.12f
                    )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 13.dp, horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Углеводы",
                        modifier = Modifier.padding(0.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black.copy(
                            alpha = 0.6f
                        )
                    )
                    Text(
                        text = "19,7 г",
                        modifier = Modifier.padding(0.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black.copy(
                            alpha = 0.87f
                        )
                    )
                }
                Divider(
                    thickness = 1.dp, color = Color.Black.copy(
                        alpha = 0.12f
                    )
                )
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
        ElevatedButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .padding(16.dp)
                .size(44.dp),
            colors = ButtonDefaults.elevatedButtonColors(containerColor = Color.White),
            elevation =ButtonDefaults.elevatedButtonElevation(defaultElevation = 8.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_left_icon),
                contentDescription = "Go back button",
                tint = Color.Black
            )
        }

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
                    text = "В корзину за ",
                    modifier = Modifier.padding(0.dp),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
                Text(
                    text = "720 Р",
                    modifier = Modifier.padding(0.dp),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
            }
        }

    }
}

//@Preview
//@Composable
//fun CardPrev() {
//    FoodiesTheme {
//        ItemCardScreen()
//    }
//}