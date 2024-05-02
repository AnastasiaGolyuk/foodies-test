package test.team.nti.foodies.data.repository

import test.team.nti.foodies.data.entity.CartItem

interface CartRepository {
    fun getCartItems(): List<CartItem>
    fun saveCartItems(cartItems: List<CartItem>)

    fun addItem(itemId: Int)

    fun removeItem(itemId: Int)

    fun isItemInCart(itemId: Int): Boolean

    fun getItemQuantity(itemId: Int): Int

    fun getCartItem(itemId: Int): CartItem?
}