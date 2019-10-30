package com.example.culturaverde.Classes

import android.content.Context
import com.example.culturaverde.Models.Producto
import io.paperdb.Paper

class CategoriaProductoGlobal {

    companion object {

        fun guardarProducto(categoria: Producto) {
            Paper.book().write("categoria", categoria)
        }

        fun getProducto(): Producto {
            return Paper.book().read("categoria")
        }


    }

}