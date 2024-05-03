package test.team.nti.foodies.presentation.catalog

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import test.team.nti.foodies.api.ApiService
import test.team.nti.foodies.data.entity.TagInApp
import test.team.nti.foodies.data.repository.CartRepository
import test.team.nti.foodies.model.Category
import test.team.nti.foodies.model.Product
import test.team.nti.foodies.model.Tag
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val apiService: ApiService,
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _categories = mutableStateListOf<Category>()
    val categories: SnapshotStateList<Category> = _categories

    private val _selectedCategory = mutableStateOf("")
    val selectedCategory: State<String> = _selectedCategory

    private val _tags = mutableStateListOf<Tag>()

    private val _selectedTags = mutableStateListOf<Int>()
    val selectedTags: SnapshotStateList<Int> = _selectedTags

    private val _cartPrice = mutableIntStateOf(0)
    val cartPrice: State<Int> = _cartPrice

    private val _items = mutableStateListOf<Product>()

    private val _itemsOfCategory = mutableStateListOf<Product>()
    val itemsOfCategory: SnapshotStateList<Product> = _itemsOfCategory

    private val _itemsWithFilters = mutableStateListOf<Product>()
    val itemsWithFilters: SnapshotStateList<Product> = _itemsWithFilters

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    fun fetchCategories() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = apiService.getCategories()
                _categories.clear()
                _categories.addAll(response)
                setCategory(response[0])
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchTags() {
        viewModelScope.launch {
            try {
                val response = apiService.getTags()
                _tags.clear()
                _tags.addAll(response)
                TagInApp.setList(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchItems() {
        viewModelScope.launch {
            try {
                val response = apiService.getItems()
                _items.clear()
                _items.addAll(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun setCategory(category: Category) {
        _selectedCategory.value = category.name
        filterItems(category)
    }

    fun setFilters(tagsList: List<Int>) {
        _selectedTags.clear()
        _selectedTags.addAll(tagsList)
    }

    private fun filterItems(category: Category) {
        viewModelScope.launch {
            //Wait while _items being fetched
            while (_items.isEmpty()) {
                delay(100)
            }
            _itemsOfCategory.clear()
            _itemsOfCategory.addAll(_items.filter { it.categoryId == category.id })
            if (_selectedTags.isNotEmpty()) {
                filterTagsItem()
            }
            _isLoading.value = false
        }
    }

    fun filterTagsItem() {
        val list = mutableListOf<Product>()
        val tagsSorted = selectedTags.sorted()
        for (tagId in tagsSorted) {
            //If it's sale tag, need to check priceOld field, not tagsId
            if (tagId == 0) {
                list.addAll(_itemsOfCategory.filter { it.priceOld != null })
            } else {
                //Otherwise filter items to make sure it tags count no less then selected amount
                val filteredItems = _itemsOfCategory.filter { it.tagIds.size >= tagsSorted.size }
                //On each iteration remove items, that where previously added,
                //but don't contain needed tags
                if (list.isNotEmpty()){
                    list.removeIf { !it.tagIds.contains(tagId) }
                } else {
                    //And add items with selected tag
                    list.addAll(filteredItems.filter { it.tagIds.contains(tagId) })
                }
            }
        }
        _itemsWithFilters.clear()
        _itemsWithFilters.addAll(list)
    }

    fun addItemToCart(itemId: Int) {
        cartRepository.addItem(itemId)
        _cartPrice.intValue += getItemPrice(itemId)
    }

    fun removeItemFromCart(itemId: Int) {
        cartRepository.removeItem(itemId)
        _cartPrice.intValue -= getItemPrice(itemId)
    }

    fun setCartPrice() {
        _cartPrice.intValue = 0
        val cartItems = cartRepository.getCartItems()
        for (index in cartItems.indices) {
            _cartPrice.intValue += getItemPrice(cartItems[index].itemId) * cartItems[index].quantity
        }
    }

    fun getItemQuantity(itemId: Int): Int {
        return cartRepository.getItemQuantity(itemId)
    }

    private fun getItemPrice(itemId: Int): Int {
        return _items.first { it.id == itemId }.priceCurrent
    }
}