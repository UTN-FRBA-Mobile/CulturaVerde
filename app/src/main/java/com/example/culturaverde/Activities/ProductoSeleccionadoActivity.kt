package com.example.culturaverde.Activities

import android.graphics.BitmapFactory
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.View
import androidx.core.content.ContextCompat
import com.example.culturaverde.Classes.ProductoGlobal
import com.example.culturaverde.R
import kotlinx.android.synthetic.main.product_row_item.view.*
import kotlinx.android.synthetic.main.producto_seleccionado_activity.*

class ProductoSeleccionadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.producto_seleccionado_activity)

        setSupportActionBar(toolbar2)

        toolbar2.navigationIcon?.setColorFilter(ContextCompat.getColor(this, R.color.common_google_signin_btn_text_dark_default), PorterDuff.Mode.SRC_ATOP)

        toolbar2.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                finish()


            }
        })

        var producto = ProductoGlobal.getProducto()

        val imageBytes = Base64.decode(producto.imagenes[0].image, 0)
        val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

        tachado_oferta.setVisibility(View.GONE)
        oferta.setVisibility(View.GONE)

        if(producto.oferta!=null){

            if(producto.oferta!!.activo==true){

                tachado_oferta.setVisibility(View.VISIBLE)

                oferta.text= "$"+(producto.precio!! - ((producto.oferta!!.porcentaje.toString().toInt() * producto.precio!!)/100)).toString()+" (x unidad)"

                oferta.setVisibility(View.VISIBLE)


            }

        }

        product_image.setImageBitmap(image)

        product_name.text=producto.titulo
        product_stock.text=producto.stock.toString()+" / "+producto.unidad_venta
        product_price.text ="$"+producto.precio.toString()+" (x unidad)"
        product_description.text= producto.descripcion



    }
}
