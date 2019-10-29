package com.example.culturaverde.Classes

import com.example.culturaverde.Models.ProductoProductor
import io.paperdb.Paper

class ProductoGlobal {

    companion object {

        fun guardarProducto(producto:ProductoProductor) {
            Paper.book().write("producto", producto)
        }

        fun getProducto(): ProductoProductor {
            return Paper.book().read("producto")
        }

    }

}