package test.team.nti.foodies.presentation.catalog.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import test.team.nti.foodies.R
import test.team.nti.foodies.ui.theme.OrangePrimary

@Composable
fun CatalogHeader(
    selectedTags: List<Int>,
    onFilterButtonClick: () -> Unit,
    onSearchButtonClick: () -> Unit
) {
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
            IconButton(onClick = { onFilterButtonClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.filter_icon),
                    contentDescription = stringResource(R.string.filter_button)
                )
            }
            if (selectedTags.isNotEmpty()) {
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
                        text = "${selectedTags.size}",
                        modifier = Modifier.padding(0.dp),
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(R.string.app_logo)
        )
        IconButton(onClick = { onSearchButtonClick() }) {
            Icon(
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = stringResource(R.string.search_button)
            )
        }
    }
}