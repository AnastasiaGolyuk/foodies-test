package test.team.nti.foodies.presentation.item_card.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import test.team.nti.foodies.R
import test.team.nti.foodies.model.Product

@Composable
fun ItemInfoList(item: Product) {

    val labels = listOf(stringResource(R.string.weight),
        stringResource(R.string.energy_value), stringResource(R.string.proteins),
        stringResource(R.string.fats), stringResource(R.string.carbohydrates)
    )
    val values = listOf(
        "${item.measure} ${item.measureUnit}",
        stringResource(R.string.energy_value_measurements, item.energyPer100Grams),
        stringResource(R.string.proteins_measurements, item.proteinsPer100Grams),
        stringResource(R.string.fats_measurements, item.fatsPer100Grams),
        stringResource(R.string.carbohydrates_measurements, item.carbohydratesPer100Grams)
    )

    val map: Map<String, String> = labels.zip(values).toMap()

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        for (entry in map){
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
                    text = entry.key,
                    modifier = Modifier.padding(0.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black.copy(
                        alpha = 0.6f
                    )
                )
                Text(
                    text = entry.value,
                    modifier = Modifier.padding(0.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black.copy(
                        alpha = 0.87f
                    )
                )
            }
        }
        Divider(
            thickness = 1.dp, color = Color.Black.copy(
                alpha = 0.12f
            )
        )
        Spacer(modifier = Modifier.height(100.dp))
    }
}