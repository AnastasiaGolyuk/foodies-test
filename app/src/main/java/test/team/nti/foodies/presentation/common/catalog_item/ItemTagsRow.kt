package test.team.nti.foodies.presentation.common.catalog_item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import test.team.nti.foodies.R
import test.team.nti.foodies.data.entity.TagInApp
import test.team.nti.foodies.model.Product

@Composable
fun ItemTagsRow(item: Product) {
    Row {
        if (item.priceOld != null) {
            Image(
                painter = painterResource(id = R.drawable.sale_tag),
                contentDescription = stringResource(id = R.string.item_image),
                modifier = Modifier
                    .padding(top = 8.dp, start = 8.dp)
                    .size(24.dp)
            )
        }
        for (tag in item.tagIds) {
            val iconId = getTagIconId(tag)
            Image(
                painter = painterResource(id = iconId),
                contentDescription = stringResource(id = R.string.item_image),
                modifier = Modifier
                    .padding(top = 8.dp, start = 8.dp)
                    .size(24.dp)
            )
        }
    }
}

fun getTagIconId(tagId: Int): Int {
    return TagInApp.getIconId(tagId)
}