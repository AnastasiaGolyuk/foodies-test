package test.team.nti.foodies.api

import retrofit2.http.GET
import test.team.nti.foodies.model.Category
import test.team.nti.foodies.model.Product
import test.team.nti.foodies.model.Tag

interface ApiService {

    @GET("Categories.json")
    suspend fun getCategories(): List<Category>

    @GET("Tags.json")
    suspend fun getTags(): List<Tag>


    @GET("Products.json")
    suspend fun getItems(): List<Product>

}