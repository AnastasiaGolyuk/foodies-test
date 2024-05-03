package test.team.nti.foodies.presentation.search.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import test.team.nti.foodies.R
import test.team.nti.foodies.ui.theme.OrangePrimary

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchField(
    input: String,
    onInputChange: (String) -> Unit,
    onGoBackButtonClick: () -> Unit,
    onClearInputButtonClick: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    TextField(
        value = input,
        onValueChange = {
            onInputChange(it)
        },
        placeholder = {
            Text(
                text = stringResource(R.string.search_field_placeholder), style = MaterialTheme.typography.bodyMedium,
                color = Color.Black.copy(alpha = 0.6f)
            )
        },
        leadingIcon = {
            IconButton(
                onClick = {
                    onGoBackButtonClick()
                }, modifier = Modifier
                    .padding(0.dp)
                    .size(24.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_left_icon),
                    contentDescription = stringResource(R.string.go_back_button),
                    tint = OrangePrimary
                )
            }
        },
        trailingIcon = {
            if (input.isNotBlank()) {
                IconButton(
                    onClick = {
                        onClearInputButtonClick()
                    }, modifier = Modifier
                        .padding(0.dp)
                        .size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.cancel_icon),
                        contentDescription = stringResource(R.string.clear_search_string),
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
            unfocusedPlaceholderColor = Color.Black.copy(alpha = 0.6f),
            focusedPlaceholderColor = Color.Black.copy(alpha = 0.6f),
            unfocusedTextColor = Color.Black.copy(alpha = 0.87f),
            focusedTextColor = Color.Black.copy(alpha = 0.87f),
        ),
        textStyle = MaterialTheme.typography.bodyMedium,
    )
}