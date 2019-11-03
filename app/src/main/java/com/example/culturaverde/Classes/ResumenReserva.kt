package com.example.culturaverde.Classes

import android.content.Context
import com.example.culturaverde.Models.CartItem
import io.paperdb.Paper

class ResumenReserva {

    companion object {

        fun addItem(cartItem: CartItem): Boolean {
            val cart = ResumenReserva.getCart()

            val targetItem = cart.singleOrNull { it.product.id == cartItem.product.id }
            if (targetItem == null) {

                if(cart.size==0) {

                    cartItem.quantity++
                    cart.add(cartItem)
                    ResumenReserva.saveCart(cart)
                    return true

                }else{

                    if(cart[0].product.productor!!.id==cartItem.product.productor!!.id){

                        cartItem.quantity++
                        cart.add(cartItem)
                        ResumenReserva.saveCart(cart)
                        return true
                    }

                    return false

                }

            } else {

                if(targetItem.product.stock!!>targetItem.quantity) {
                    targetItem.quantity++
                    ResumenReserva.saveCart(cart)
                    return true
                }else{

                    return false
                }
            }
        }

        fun removeItem(cartItem: CartItem, context: Context):Boolean {
            val cart = ResumenReserva.getCart()

            val targetItem = cart.singleOrNull { it.product.id == cartItem.product.id }
            if (targetItem != null) {
                if (targetItem.quantity > 0) {
                    targetItem.quantity--

                    if (targetItem.quantity == 0) {
                        cart.remove(targetItem)
                        ResumenReserva.saveCart(cart)
                        return false
                    }
                    ResumenReserva.saveCart(cart)
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
            ResumenReserva.getCart().forEach {
                cartSize += it.quantity;
            }

            return cartSize
        }

    }
}