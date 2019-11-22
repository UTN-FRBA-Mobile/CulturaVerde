package com.example.culturaverde.Activities

import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.culturaverde.Adapters.ShoppingCartAdapter
import com.example.culturaverde.Classes.ShoppingCart
import com.example.culturaverde.R
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.culturaverde.Classes.ProductorGlobal
import kotlinx.android.synthetic.main.activity_shopping_cart.*
import kotlinx.android.synthetic.main.activity_shopping_cart.toolbar

class ShoppingCartActivity : AppCompatActivity() {

    lateinit var adapter: ShoppingCartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)

        setSupportActionBar(toolbar)

        val objetoIntent: Intent = intent
        var idproductor: String? = objetoIntent.getStringExtra("Idproductor")

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material)
        upArrow?.setColorFilter(ContextCompat.getColor(this, R.color.color_verde), PorterDuff.Mode.SRC_ATOP)
        supportActionBar?.setHomeAsUpIndicator(upArrow)

        adapter = ShoppingCartAdapter(this, ShoppingCart.getCart())
        adapter.notifyDataSetChanged()

        shopping_cart_recyclerView.adapter = adapter

        shopping_cart_recyclerView.layoutManager = LinearLayoutManager(this)

        actualizarPrecioTotal()

        botonfinalizarreserva.setOnClickListener {

            if(ShoppingCart.getCart().size!=0) {

                    var id_productor = ShoppingCart.getCart()[0].product.productor!!.id

                    val intent = Intent(this, FinalizarReservaActivity::class.java)
                    intent.putExtra("Idproductor", id_productor.toString())
                    startActivity(intent)

            }else{

                Toast.makeText(this@ShoppingCartActivity, "Tenés que agregar algún producto al carrito!", Toast.LENGTH_SHORT).show()


            }

        }
    }

    fun actualizarPrecioTotal(){

        var productos_carrito = ShoppingCart.getCart()

        productos_carrito.forEach { p ->

            if(p.product.oferta!=null && p.product.oferta!!.activo==true){

                var nuevo_precio = (p.product.precio!! - ((p.product.oferta!!.porcentaje!!.toInt() * p.product.precio!!) / 100)).toString()

        p.product.precio= nuevo_precio.toFloat()

            }


        }

        var totalPrice = ShoppingCart.getCart()
            .fold(0.toDouble()) { acc, cartItem -> acc + cartItem.quantity.times(cartItem.product.precio!!.toDouble()) }

        total_price.text = "$${totalPrice}"


    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
