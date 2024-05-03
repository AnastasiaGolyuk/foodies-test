package test.team.nti.foodies.data.repository.repositoryImpl

import test.team.nti.foodies.data.entity.CartItem
import test.team.nti.foodies.data.repository.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor() : CartRepository {

    private val cartItems = mutableListOf<CartItem>()

    override fun getCartItems(): List<CartItem> {
        return cartItems
    }

    override fun saveCartItems(cartItems: List<CartItem>) {
        this.cartItems.clear()
        this.cartItems.addAll(cartItems)
    }

    override fun addItem(itemId: Int) {
        val index = cartItems.indexOfFirst { it.itemId == itemId }
        if (index == -1) {
            cartItems.add(CartItem(itemId, 1))
        } else {
            cartItems[index] = CartItem(itemId, cartItems[index].quantity + 1)
        }
    }

    override fun removeItem(itemId: Int) {
        val index = cartItems.indexOfFirst { it.itemId == itemId }
        if (index != -1) {
            val quantity = cartItems[index].quantity
            if (quantity > 1) {
                cartItems[index] = CartItem(itemId, quantity - 1)
            } else {
                cartItems.removeAt(index)
            }
        }
    }

    override fun isItemInCart(itemId: Int): Boolean {
        val index = cartItems.indexOfFirst { it.itemId == itemId }
        return index != -1
    }

    override fun getItemQuantity(itemId: Int): Int {
        val cartItem: CartItem?
        try {
            cartItem = cartItems.first { it.itemId == itemId }
        } catch (e: NoSuchElementException) {
            e.printStackTrace()
            return 0
        }
        return cartItem.quantity
    }

    override fun getCartItem(itemId: Int): CartItem? {
        var cartItem: CartItem? = null
        try {
            cartItem = cartItems.first { it.itemId == itemId }
        } catch (e: NoSuchElementException) {
            e.printStackTrace()
        }
        return cartItem
    }
}