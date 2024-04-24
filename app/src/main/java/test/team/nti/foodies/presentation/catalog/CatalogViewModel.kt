package test.team.nti.foodies.presentation.catalog

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import test.team.nti.foodies.api.ApiService
import test.team.nti.foodies.model.Category
import test.team.nti.foodies.model.Product
import test.team.nti.foodies.model.Tag
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    private val _categories = mutableStateListOf<Category>()
    val categories: SnapshotStateList<Category> = _categories

    private val _selectedCategory = mutableStateOf("")
    val selectedCategory: State<String> = _selectedCategory

    private val _tags = mutableStateListOf<Tag>()
    val tags: SnapshotStateList<Tag> = _tags

    private val _items = mutableStateListOf<Product>()
    val items: SnapshotStateList<Product> = _items

    fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = apiService.getCategories()
                _categories.clear()
                _categories.addAll(response)
                _selectedCategory.value = response[0].name
            } catch (e: Exception) {

            }
        }
    }

    fun fetchTags() {
        viewModelScope.launch {
            try {
                val response = apiService.getTags()
                _tags.clear()
                _tags.addAll(response)
            } catch (e: Exception) {

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

            }
        }
    }

    fun setCategory(category: String) {
        _selectedCategory.value = category
    }
}