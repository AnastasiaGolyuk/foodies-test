package test.team.nti.foodies.model

import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)
