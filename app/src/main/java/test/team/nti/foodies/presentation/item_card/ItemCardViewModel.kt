package test.team.nti.foodies.presentation.item_card

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import test.team.nti.foodies.api.ApiService
import test.team.nti.foodies.data.repository.CartRepository
import test.team.nti.foodies.model.Product
import javax.inject.Inject

@HiltViewModel
class ItemCardViewModel @Inject constructor(
    private val apiService: ApiService,
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _item = mutableStateOf<Product?>(null)
    val item: State<Product?> = _item

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _isItemInTheCart = mutableStateOf(false)
    val isItemInTheCart: State<Boolean> = _isItemInTheCart

    private val _itemQuantity = mutableIntStateOf(0)
    val itemQuantity: State<Int> = _itemQuantity

    fun setItem(itemId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = apiService.getItems()
                _item.value = response.first { it.id == itemId }
                _isItemInTheCart.value = cartRepository.isItemInCart(itemId)
                if (_isItemInTheCart.value) {
                    _itemQuantity.intValue = cartRepository.getItemQuantity(itemId)
                }
                _isLoading.value = false
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addItemToCart(itemId: Int) {
        cartRepository.addItem(itemId)
        _itemQuantity.intValue = cartRepository.getItemQuantity(itemId)
        _isItemInTheCart.value = cartRepository.isItemInCart(itemId)
    }

    fun removeItemFromCart(itemId: Int) {
        cartRepository.removeItem(itemId)
        _itemQuantity.intValue = cartRepository.getItemQuantity(itemId)
        _isItemInTheCart.value = cartRepository.isItemInCart(itemId)
    }
}