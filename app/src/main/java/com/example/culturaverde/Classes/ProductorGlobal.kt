package com.example.culturaverde.Classes

import com.example.culturaverde.Models.ProductorMaps
import io.paperdb.Paper

class ProductorGlobal {

    companion object{
        fun guardarProductor(productor: ProductorMaps){
            Paper.book().write("productormaps", productor)
        }

        fun  getProductor(): ProductorMaps{
            return Paper.book().read("productormaps")
        }
    }
}