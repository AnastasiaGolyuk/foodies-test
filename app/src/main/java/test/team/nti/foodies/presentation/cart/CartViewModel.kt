package test.team.nti.foodies.presentation.cart

import android.util.Log
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
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val apiService: ApiService,
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _items = mutableStateListOf<Product>()

    private val _itemsInCart = mutableStateListOf<Product>()
    val itemsInCart: SnapshotStateList<Product> = _itemsInCart

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
                setCartItems(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun setCartItems(items: List<Product>) {
        val cartItems = cartRepository.getCartItems()
        val cartItemsValues = mutableListOf<Product>()
        for (cartItem in cartItems) {
            try {
                cartItemsValues.add(items.first { cartItem.itemId == it.id })
            } catch (e: NoSuchElementException) {
                Log.e("CART", "No such element in catalog")
            }
        }
        _itemsInCart.clear()
        //Sort items from the most recent, added to the cart, to the first
        _itemsInCart.addAll(cartItemsValues.reversed())
        setCartPrice()
    }

    fun getItemQuantity(itemId: Int): Int {
        return cartRepository.getItemQuantity(itemId)
    }

    private fun setCartPrice() {
        viewModelScope.launch {
            while (_itemsInCart.isEmpty()) {
                delay(100)
            }
            //Sort items from the most recent, added to the cart, to first
            val cartItems = cartRepository.getCartItems().reversed()
            for (index in _itemsInCart.indices) {
                _cartPrice.intValue += _itemsInCart[index].priceCurrent * cartItems[index].quantity
            }
            _isLoading.value = false
        }
    }

    fun addItemToCart(itemId: Int) {
        cartRepository.addItem(itemId)
        _cartPrice.intValue += getItemPrice(itemId)
    }

    fun removeItemFromCart(itemId: Int) {
        cartRepository.removeItem(itemId)
        //If item was fully removed from cart
        if (!cartRepository.isItemInCart(itemId)) {
            _itemsInCart.removeIf { it.id == itemId }
        }
        _cartPrice.intValue -= getItemPrice(itemId)
    }

    private fun getItemPrice(itemId: Int): Int {
        return _items.first { it.id == itemId }.priceCurrent
    }
}