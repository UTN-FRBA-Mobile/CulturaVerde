package com.example.culturaverde.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.culturaverde.Adapters.FechaEntregaAdapter
import com.example.culturaverde.Controllers.FechaEntregaControlador
import com.example.culturaverde.Models.FechaEntrega
import com.example.culturaverde.R
import com.example.culturaverde.Services.APIConfig
import kotlinx.android.synthetic.main.fragment_busquedafechasentrega.*
import kotlinx.android.synthetic.main.fragment_busquedapuntosentrega.swipeRefreshLayout4
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BusquedaFechasEntregaActivity : AppCompatActivity() {


    private var fechaEntregaControlador: FechaEntregaControlador = APIConfig.getRetrofitClient(this).create(FechaEntregaControlador::class.java)
    private lateinit var fechasEntregaAdapter: FechaEntregaAdapter
    private var fechasentrega = listOf<FechaEntrega>()
    var idpunto_entrega = Long


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_busquedafechasentrega)


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

        var idptoentrega: Long = idpuntoentrega!!.toLong()

        swipeRefreshLayout4.setColorSchemeColors(ContextCompat.getColor(this, R.color.color_verde))

        swipeRefreshLayout4.isRefreshing = true

        recycler_fechasentrega.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        getfechasentrega(idptoentrega, idproductor, nombreretira, apellidoretira, idpuntoentrega, direccioncompleta,
            fechaentregaid, fechaentrega, horariodesde, horariohasta)

    }


    fun getfechasentrega(idptoentrega: Long, idproductor: String?, nombreretira: String?, apellidoretira: String?, idpuntoentrega: String?, direccioncompleta: String?,
                         fechaentregaid: String?, fechaentrega: String?, horariodesde: String?, horariohasta: String?) {

        fechaEntregaControlador.obtenerFechasPuntoDeEntrega(idptoentrega)
            .enqueue(object : Callback<List<FechaEntrega>> {
                override fun onFailure(call: Call<List<FechaEntrega>>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<List<FechaEntrega>>,
                    response: Response<List<FechaEntrega>>
                ) {

                    swipeRefreshLayout4.isRefreshing = false
                    swipeRefreshLayout4.isEnabled = false

                    if (response != null) {

                        fechasentrega = response.body()!!

                        fechasEntregaAdapter = FechaEntregaAdapter(this@BusquedaFechasEntregaActivity, fechasentrega, idproductor, nombreretira, apellidoretira, idpuntoentrega, direccioncompleta, fechaentregaid, fechaentrega, horariodesde, horariohasta)
                        recycler_fechasentrega.adapter = fechasEntregaAdapter
                        fechasEntregaAdapter.notifyDataSetChanged()
                    }
                }

            })
    }


}