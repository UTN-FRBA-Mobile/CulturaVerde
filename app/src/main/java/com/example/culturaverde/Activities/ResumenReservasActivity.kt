package com.example.culturaverde.Activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.culturaverde.Adapters.ResumenReservasAdapter
import com.example.culturaverde.Classes.ShoppingCart
import com.example.culturaverde.Classes.UsuarioGlobal
import com.example.culturaverde.Controllers.ReservasControlador
import com.example.culturaverde.Models.*
import com.example.culturaverde.R
import com.example.culturaverde.Services.APIConfig
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.busquedafechasentrega_items.*
import kotlinx.android.synthetic.main.fragment_finalizarreserva.*
import kotlinx.android.synthetic.main.fragment_resumenreservas.*
import kotlinx.android.synthetic.main.reservas_item.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


class ResumenReservasActivity : AppCompatActivity() {

    private var reservasControlador: ReservasControlador =
        APIConfig.getRetrofitClient(this).create(ReservasControlador::class.java)
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
        } else {
            datosparaelretiropersona.text = "Retira $nombreretira $apellidoretira"
            datospuntoentregaoacuerdo.text = comoretira
            datospuntoentregadireccion.text = direccion
            datospuntoentregafechaentrega.text = "A partir del día "+fechaentrega
            datospuntoentregahorario.text = horario

            datospuntoentregadireccion.visibility = View.VISIBLE
            datospuntoentregafechaentrega.visibility = View.VISIBLE
            datospuntoentregahorario.visibility = View.VISIBLE

        }

        adapter = ResumenReservasAdapter(this, ShoppingCart.getCart())
        adapter.notifyDataSetChanged()


        resumenproductosselect_recyclerView.adapter = adapter
        resumenproductosselect_recyclerView.layoutManager = LinearLayoutManager(this)


        botonFinalizarReserva3.setOnClickListener {

            generarReserva()
        }


    }


    fun generarReserva() {

        val objetoIntent: Intent = intent

        var nombreretira: String? = objetoIntent.getStringExtra("Nombreretira")
        var apellidoretira: String? = objetoIntent.getStringExtra("Apellidoretira")
        var opcion: String? = objetoIntent.getStringExtra("Opcion")
        var fechaentrega: String? = objetoIntent.getStringExtra("Fechaentrega")
        var id_punto_entrega: String? = objetoIntent.getStringExtra("id_punto_entrega")
        var id_productor: String? = objetoIntent.getStringExtra("id_productor")

        var totalPrice = ShoppingCart.getCart()
            .fold(0.toDouble()) { acc, cartItem -> acc + cartItem.quantity.times(cartItem.product.precio!!.toDouble()) }

        var reserva = JSONObject()

        var fecha_hoy = Date()

        var productor_json = JSONObject()

        var consumidor_json = JSONObject()

        var punto_entrega_json = JSONObject()

        var estado_reserva_json = JSONObject()

        productor_json.put("id", id_productor!!.toLong())

        consumidor_json.put("id", UsuarioGlobal.getUsuario()!!.id)

        estado_reserva_json.put("id",1.toLong())

        reserva.put("productor", productor_json)
        reserva.put("consumidor", consumidor_json)
        reserva.put("fecha", "")
        reserva.put("estado_reserva", estado_reserva_json)
        reserva.put("total_reserva", totalPrice.toString())
        reserva.put("persona_retiro", nombreretira + " " + apellidoretira)
        reserva.put("forma_retiro", "Acuerda con Productor")
        reserva.put("fecha_creacion", java.sql.Date(fecha_hoy.time))


        if (opcion!!.toInt() === 2) {


            var date = SimpleDateFormat("dd-MM-yyyy").parse(fechaentrega)

            punto_entrega_json.put("id", id_punto_entrega!!.toLong())

            reserva.put("punto_entrega", punto_entrega_json)
            reserva.put("fecha", java.sql.Date(date.time))
            reserva.put("forma_retiro", "Por punto de entrega")

        }else{

            punto_entrega_json.put("id", "")
            reserva.put("punto_entrega", punto_entrega_json)


        }

            var detallesReserva = JSONArray()


            var productos = ShoppingCart.getCart()

            productos.forEach { product ->

              //  var producto_reserva = DetalleReserva(null,product.product.id!!.toLong(),product.product,product.quantity,true,product.product.precio!!.toFloat() )

                var producto = JSONObject()

                producto.put("id_producto", product.product.id!!.toLong())
                producto.put("activo", true)
                producto.put("cantidad", product.quantity)
                producto.put("precio_por_unidad", product.product.precio)

                detallesReserva.put(producto)
            }

            reserva.put("detallesReserva", detallesReserva)


            reservasControlador.generarReserva(reserva.toString())
                .enqueue(object : retrofit2.Callback<Void> {
                    override fun onFailure(call: Call<Void>, t: Throwable) {

                        Toast.makeText(
                            this@ResumenReservasActivity,
                            "Ocurrió un error inesperado, intentá nuevamente",
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                    override fun onResponse(
                        call: Call<Void>,
                        response: Response<Void>
                    ) {

                        Toast.makeText(
                            this@ResumenReservasActivity,
                            "Reserva generada correctamente!",
                            Toast.LENGTH_SHORT
                        ).show()


                    }

                })


        }


    }

