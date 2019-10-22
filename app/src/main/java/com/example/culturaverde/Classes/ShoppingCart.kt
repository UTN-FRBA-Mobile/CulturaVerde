package com.example.culturaverde.Classes

import android.content.Context
import com.example.culturaverde.Models.CartItem
import io.paperdb.Paper

class ShoppingCart {

    companion object {
        fun addItem(cartItem: CartItem): Boolean {
            val cart = ShoppingCart.getCart()

            val targetItem = cart.singleOrNull { it.product.id == cartItem.product.id }
            if (targetItem == null) {

                    cartItem.quantity++
                    cart.add(cartItem)
                    ShoppingCart.saveCart(cart)
                    return true

                } else {

                if(targetItem.product.stock!!>targetItem.quantity) {
                    targetItem.quantity++
                    ShoppingCart.saveCart(cart)
                    return true
                }else{

                    return false
                }
            }
        }

        fun removeItem(cartItem: CartItem,context: Context):Boolean {
            val cart = ShoppingCart.getCart()

            val targetItem = cart.singleOrNull { it.product.id == cartItem.product.id }
            if (targetItem != null) {
                if (targetItem.quantity > 0) {
                    targetItem.quantity--

                    if (targetItem.quantity == 0) {
                        cart.remove(targetItem)
                        ShoppingCart.saveCart(cart)
                        return false
                    }
                    ShoppingCart.saveCart(cart)
                    return true
                }
            }

            return false
        }

        fun saveCart(cart: MutableList<CartItem>) {
            Paper.book().write("cart", cart)
        }

        fun getCart(): MutableList<CartItem> {
            return Paper.book().read("cart", mutableListOf())
        }

        fun getShoppingCartSize(): Int {
            var cartSize = 0
            ShoppingCart.getCart().forEach {
                cartSize += it.quantity;
            }

            return cartSize
        }

    }
}