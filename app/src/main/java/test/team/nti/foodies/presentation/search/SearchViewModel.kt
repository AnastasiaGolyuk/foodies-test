package test.team.nti.foodies.presentation.search

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
import test.team.nti.foodies.data.repository.CartRepository
import test.team.nti.foodies.model.Product
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val apiService: ApiService,
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _items = mutableStateListOf<Product>()

    private val _searchString = mutableStateOf("")
    val searchString: State<String> = _searchString

    private val _itemsOfSearch = mutableStateListOf<Product>()
    val itemsOfSearch: SnapshotStateList<Product> = _itemsOfSearch

    private val _cartPrice = mutableIntStateOf(0)
    val cartPrice: State<Int> = _cartPrice

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    fun fetchItems() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = apiService.getItems()
                _items.clear()
                _items.addAll(response)
                _isLoading.value = false
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun onInputChange(text: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _searchString.value=text
            _itemsOfSearch.clear()
            if (text.isNotBlank()) {
                _itemsOfSearch.addAll(_items.filter {
                    it.name.lowercase(Locale.ROOT).contains(text.lowercase(Locale.ROOT))
                })
            }
            _isLoading.value = false
        }
    }

    fun cleanSearchFiled(){
        _searchString.value=""
        _itemsOfSearch.clear()
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
        viewModelScope.launch {
            while (_items.isEmpty()){
                delay(100)
            }
            _cartPrice.intValue = 0
            val cartItems = cartRepository.getCartItems()
            for (index in cartItems.indices) {
                _cartPrice.intValue += getItemPrice(cartItems[index].itemId) * cartItems[index].quantity
            }
        }
    }

    fun getItemQuantity(itemId: Int): Int {
        return cartRepository.getItemQuantity(itemId)
    }

    private fun getItemPrice(itemId: Int): Int {
        return _items.first { it.id == itemId }.priceCurrent
    }
}