package test.team.nti.foodies.presentation.catalog.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import test.team.nti.foodies.R
import test.team.nti.foodies.data.entity.TagInApp
import test.team.nti.foodies.ui.theme.OrangePrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterModalDialog(
    selectedFilters: List<Int>,
    onDismiss: (Boolean) -> Unit,
    onDoneClick: (List<Int>, Boolean) -> Unit
) {

    val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val checkedFilters = remember {
        mutableStateListOf<Int>()
    }

    LaunchedEffect(Unit) {
        if (selectedFilters.isNotEmpty()) {
            checkedFilters.addAll(selectedFilters)
        }
    }

    ModalBottomSheet(
        onDismissRequest = { onDismiss(false) },
        sheetState = modalBottomSheetState,
        containerColor = Color.White,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Column(
            modifier = Modifier.padding(vertical = 32.dp, horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.filters_label),
                modifier = Modifier.padding(0.dp),
                style = MaterialTheme.typography.headlineLarge,
                color = Color.Black.copy(alpha = 0.6f),
                textAlign = TextAlign.Start
            )
            LazyColumn {
                items(items = TagInApp.getList()) { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item.name,
                            modifier = Modifier.padding(0.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black.copy(alpha = 0.6f),
                            textAlign = TextAlign.Start
                        )
                        Checkbox(
                            checked = checkedFilters.contains(item.id),
                            onCheckedChange = {
                                if (checkedFilters.contains(item.id)) {
                                    checkedFilters.remove(item.id)
                                } else {
                                    checkedFilters.add(item.id)
                                }
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = OrangePrimary,
                                uncheckedColor = Color.Black.copy(alpha = 0.12f)
                            )
                        )
                    }
                    Divider(color = Color.Black.copy(alpha = 0.12f), thickness = 1.dp)
                }
            }
            Button(
                onClick = { onDoneClick(checkedFilters, false) },
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
            ) {
                Text(
                    text = stringResource(R.string.done_button_label),
                    modifier = Modifier.padding(0.dp),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
            }
        }
    }
}
