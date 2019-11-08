package com.example.culturaverde.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.culturaverde.Adapters.PuntosEntregaAdapter
import com.example.culturaverde.Controllers.PuntosentregaControlador
import com.example.culturaverde.Models.PuntosEntrega
import com.example.culturaverde.R
import com.example.culturaverde.Services.APIConfig
import com.example.culturaverde.ViewModels.BusquedaPuntosEntregaViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_resultado_busqueda.*
import kotlinx.android.synthetic.main.busquedapuntosentrega_items.*
import kotlinx.android.synthetic.main.fragment_busquedapuntosentrega.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BusquedaPuntosEntregaActivity: AppCompatActivity() {

    private var puntosEntregaControlador: PuntosentregaControlador = APIConfig.getRetrofitClient(this).create(PuntosentregaControlador::class.java)

    private lateinit var puntosEntregaAdapter: PuntosEntregaAdapter
    private var puntosentrega = listOf<PuntosEntrega>()
    var idprod = Long

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_busquedapuntosentrega)

        val objetoIntent: Intent = intent
        var idproductor: String? = objetoIntent.getStringExtra("Idproductor")
        var nombreretira: String? = objetoIntent.getStringExtra("Nombreretira")
        var apellidoretira: String? = objetoIntent.getStringExtra("Apellidoretira")

        var idpuntoentrega: String? = objetoIntent.getStringExtra("Idpuntoentrega")
        var direccioncompleta: String? = objetoIntent.getStringExtra("Direccioncompleta")

        var fechaentregaid: String? = objetoIntent.getStringExtra("Fechaentregaid")
        var fechaentrega: String? = objetoIntent.getStringExtra("Fechaentrega")

        var horariodesde: String? = objetoIntent.getStringExtra("Horariodesde")
        var horariohasta: String? = objetoIntent.getStringExtra("Horariohasta")

        var idprod: Long = idproductor!!.toLong()

        swipeRefreshLayout4.setColorSchemeColors(ContextCompat.getColor(this, R.color.color_verde))

        swipeRefreshLayout4.isRefreshing = true

        recycler_puntosentrega.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        getpuntosdeentrega(idprod, idproductor, nombreretira, apellidoretira, idpuntoentrega, direccioncompleta,
            fechaentregaid, fechaentrega, horariodesde, horariohasta)

    }



    fun getpuntosdeentrega(idprod: Long, idproductor: String?, nombreretira: String?, apellidoretira: String?, idpuntoentrega: String?, direccioncompleta: String?,
                           fechaentregaid: String?, fechaentrega: String?, horariodesde: String?, horariohasta: String?) {
        puntosEntregaControlador.obtenerpuntosdeentrega(idprod)
            .enqueue(object : Callback<List<PuntosEntrega>> {
                override fun onFailure(call: Call<List<PuntosEntrega>>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<List<PuntosEntrega>>,
                    response: Response<List<PuntosEntrega>>
                ) {

                    swipeRefreshLayout4.isRefreshing = false
                    swipeRefreshLayout4.isEnabled = false

                    if (response != null) {

                        puntosentrega = response.body()!!

                        puntosEntregaAdapter = PuntosEntregaAdapter(this@BusquedaPuntosEntregaActivity, puntosentrega, idproductor, nombreretira, apellidoretira, idpuntoentrega, direccioncompleta, fechaentregaid, fechaentrega, horariodesde, horariohasta)
                        recycler_puntosentrega.adapter = puntosEntregaAdapter
                        puntosEntregaAdapter.notifyDataSetChanged()
                    }
                }

            })
    }

}