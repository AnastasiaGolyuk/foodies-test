package test.team.nti.foodies.data.repository

import test.team.nti.foodies.data.entity.CartItem

/**
 * Interface to interact with cart
 */
interface CartRepository {

    /**
     * Get cart items
     *
     * @return list of cart items, added to the cart
     */
    fun getCartItems(): List<CartItem>

    /**
     * Save items to the cart
     *
     * @param cartItems list, that needed to be saved
     */
    fun saveCartItems(cartItems: List<CartItem>)

    /**
     * Add item to the cart
     *
     * @param itemId - id of item to be saved
     */
    fun addItem(itemId: Int)

    /**
     * Remove item from the cart
     *
     * @param itemId - id of item to be removed
     */
    fun removeItem(itemId: Int)

    /**
     * Is item in cart
     *
     * @param itemId - id of item to check if it's in the cart
     * @return true - if item with given id is in the cart, false otherwise
     */
    fun isItemInCart(itemId: Int): Boolean

    /**
     * Get item quantity
     *
     * @param itemId - id of item which quantity should be counted
     * @return quantity of item added in the cart
     */
    fun getItemQuantity(itemId: Int): Int

    /**
     * Get cart item
     *
     * @param itemId - id of item which should be returned
     * @return CartItem object that associated with given item id
     */
    fun getCartItem(itemId: Int): CartItem?
}