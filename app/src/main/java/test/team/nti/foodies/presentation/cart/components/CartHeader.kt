package test.team.nti.foodies.presentation.cart.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.zIndex
import test.team.nti.foodies.R
import test.team.nti.foodies.ui.theme.OrangePrimary

@Composable
fun BoxScope.CartHeader(onBackButtonClick:()->Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.TopCenter)
            .zIndex(10f)
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { onBackButtonClick() }, modifier = Modifier
                .padding(0.dp)
                .size(24.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_left_icon),
                contentDescription = stringResource(id = R.string.go_back_button),
                tint = OrangePrimary
            )
        }
        Text(
            text = stringResource(R.string.cart_label), style = MaterialTheme.typography.headlineMedium,
            color = Color.Black.copy(alpha = 0.87f)
        )
    }
}