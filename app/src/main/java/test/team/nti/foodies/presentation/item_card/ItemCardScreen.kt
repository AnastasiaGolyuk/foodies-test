package test.team.nti.foodies.presentation.item_card

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import test.team.nti.foodies.R
import test.team.nti.foodies.ui.theme.OrangePrimary

@Composable
fun ItemCardScreen(navController: NavController, viewModel: ItemCardViewModel, itemId: Int) {

    LaunchedEffect(Unit) {
        viewModel.setItem(itemId)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        if (!viewModel.isLoading.value) {
            val item = viewModel.item.value!!
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(24.dp)
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
                        text = item.name,
                        modifier = Modifier.padding(0.dp),
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.Black.copy(
                            alpha = 0.87f
                        )
                    )
                    Text(
                        text = item.description.replace("  ", "\n"),
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
                            text = "${item.measure} ${item.measureUnit}",
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
                            text = "${item.energyPer100Grams} ккал",
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
                            text = "${item.proteinsPer100Grams} г",
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
                            text = "${item.fatsPer100Grams} г",
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
                            text = "${item.carbohydratesPer100Grams} г",
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
                elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 8.dp),
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
                    .fillMaxWidth().shadow(elevation = 25.dp)
                    .background(color = Color.White)
            ) {
                if (!viewModel.isItemInTheCart.value) {
                    Button(
                        onClick = { viewModel.addItemToCart(item.id) },
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
                            text = "${item.priceCurrent / 100} ₽",
                            modifier = Modifier.padding(0.dp),
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White
                        )
                    }
                } else {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ElevatedButton(
                            onClick = { viewModel.removeItemFromCart(item.id) },
                            modifier = Modifier
                                .padding(0.dp)
                                .size(40.dp),
                            shape = RoundedCornerShape(size = 8.dp),
                            colors = ButtonDefaults.elevatedButtonColors(containerColor = Color.White),
                            elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 4.dp),
                            border = BorderStroke(width = 0.dp, color = Color.Transparent),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.minus_icon),
                                contentDescription = "Remove item",
                                tint = OrangePrimary
                            )
                        }
                        Text(
                            text = "${viewModel.itemQuantity.value}",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Black.copy(alpha = 0.87f)
                        )
                        ElevatedButton(
                            onClick = { viewModel.addItemToCart(item.id) },
                            modifier = Modifier
                                .padding(0.dp)
                                .size(40.dp),
                            shape = RoundedCornerShape(size = 8.dp),
                            colors = ButtonDefaults.elevatedButtonColors(containerColor = Color.White),
                            elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 4.dp),
                            border = BorderStroke(width = 0.dp, color = Color.Transparent),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.plus_icon),
                                contentDescription = "Add item",
                                tint = OrangePrimary
                            )
                        }
                    }
                }
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