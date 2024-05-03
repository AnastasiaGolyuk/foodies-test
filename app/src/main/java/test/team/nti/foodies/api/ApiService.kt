package test.team.nti.foodies.api

import retrofit2.http.GET
import test.team.nti.foodies.model.Category
import test.team.nti.foodies.model.Product
import test.team.nti.foodies.model.Tag


/**
 * Api service to fetch data
 */
interface ApiService {

    /**
     * Get categories
     *
     * @return list of product categories
     */
    @GET("Categories.json")
    suspend fun getCategories(): List<Category>

    /**
     * Get tags
     *
     * @return list of product tags
     */
    @GET("Tags.json")
    suspend fun getTags(): List<Tag>


    /**
     * Get items
     *
     * @return list of all products
     */
    @GET("Products.json")
    suspend fun getItems(): List<Product>
}