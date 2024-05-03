package test.team.nti.foodies.data.entity

import androidx.annotation.DrawableRes
import test.team.nti.foodies.R
import test.team.nti.foodies.model.Tag

data class TagInApp(val id: Int, val name: String, @DrawableRes val imageRes: Int) {
    companion object {

        private val tagsList = mutableListOf<TagInApp>()

        fun setList(tags: List<Tag>) {
            val list = mutableListOf<TagInApp>()
            list.add(TagInApp(0, "Со скидкой", R.drawable.sale_tag))
            list.add(TagInApp(1, tags[0].name, R.drawable.new_tag))
            list.add(TagInApp(2, tags[1].name, R.drawable.vegan_tag))
            list.add(TagInApp(3, tags[2].name, R.drawable.hit_tag))
            list.add(TagInApp(4, tags[3].name, R.drawable.hot_tag))
            list.add(TagInApp(5, tags[4].name, R.drawable.express_tag))

            tagsList.clear()
            tagsList.addAll(list)
        }

        fun getList(): List<TagInApp> {
            return tagsList
        }

        fun getIconId(tagId: Int): Int {
            return tagsList.first { it.id == tagId }.imageRes
        }
    }
}
