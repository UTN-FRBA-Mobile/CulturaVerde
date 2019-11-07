package com.example.culturaverde.Activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.culturaverde.Adapters.ResumenReservasAdapter
import com.example.culturaverde.Classes.ShoppingCart
import com.example.culturaverde.R
import kotlinx.android.synthetic.main.fragment_finalizarreserva.*
import kotlinx.android.synthetic.main.fragment_resumenreservas.*

class ResumenReservasActivity : AppCompatActivity() {

    lateinit var adapter: ResumenReservasAdapter
    lateinit var nombre: String
    lateinit var apellido: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_resumenreservas)

        val objetoIntent: Intent = intent

        var opcion: String? = objetoIntent.getStringExtra("Opcion")
        var nombreretira: String? = objetoIntent.getStringExtra("Nombreretira")
        var apellidoretira: String? = objetoIntent.getStringExtra("Apellidoretira")
        var comoretira: String? = objetoIntent.getStringExtra("Comoretira")

        if (opcion == "1") {
            datosparaelretiropersona.text = "Retira $nombreretira $apellidoretira"
            datospuntoentregaoacuerdo.text = comoretira
        }
        else
        {
            datosparaelretiropersona.text = "Retira $nombreretira $apellidoretira"
            datospuntoentregaoacuerdo.text = comoretira

        }

            adapter = ResumenReservasAdapter(this, ShoppingCart.getCart())
            adapter.notifyDataSetChanged()


        resumenproductosselect_recyclerView.adapter = adapter
        resumenproductosselect_recyclerView.layoutManager = LinearLayoutManager(this)


    }


}