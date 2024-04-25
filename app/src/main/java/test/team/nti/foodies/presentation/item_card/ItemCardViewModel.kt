package test.team.nti.foodies.presentation.item_card

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import test.team.nti.foodies.api.ApiService
import test.team.nti.foodies.model.Product
import javax.inject.Inject

@HiltViewModel
class ItemCardViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    private val _items = mutableStateListOf<Product>()
    val items: SnapshotStateList<Product> = _items

    private val _item = mutableStateOf<Product?>(null)
    val item: State<Product?> = _item

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    fun fetchItems() {
        viewModelScope.launch {
            try {
                val response = apiService.getItems()
                _items.clear()
                _isLoading.value = !_items.addAll(response)
            } catch (e: Exception) {

            }
        }
    }

    fun getItem(id: Int){
        println(id)
        _item.value = _items.first { it.id == id }
    }
}