package com.example.culturaverde.Activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
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

        var direccion: String? = objetoIntent.getStringExtra("Direccion")
        var fechaentrega: String? = objetoIntent.getStringExtra("Fechaentrega")
        var horario: String? = objetoIntent.getStringExtra("Horario")

        if (opcion == "1") {
            datosparaelretiropersona.text = "Retira $nombreretira $apellidoretira"
            datospuntoentregaoacuerdo.text = comoretira

            datospuntoentregadireccion.visibility = View.INVISIBLE
            datospuntoentregafechaentrega.visibility = View.INVISIBLE
            datospuntoentregahorario.visibility = View.INVISIBLE
        }
        else
        {
            datosparaelretiropersona.text = "Retira $nombreretira $apellidoretira"
            datospuntoentregaoacuerdo.text = comoretira
            datospuntoentregadireccion.text = direccion
            datospuntoentregafechaentrega.text = fechaentrega
            datospuntoentregahorario.text = horario

            datospuntoentregadireccion.visibility = View.VISIBLE
            datospuntoentregafechaentrega.visibility = View.VISIBLE
            datospuntoentregahorario.visibility = View.VISIBLE

        }

            adapter = ResumenReservasAdapter(this, ShoppingCart.getCart())
            adapter.notifyDataSetChanged()


        resumenproductosselect_recyclerView.adapter = adapter
        resumenproductosselect_recyclerView.layoutManager = LinearLayoutManager(this)


    }


}