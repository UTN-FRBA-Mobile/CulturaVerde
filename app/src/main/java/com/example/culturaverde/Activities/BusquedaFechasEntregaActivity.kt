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
    private var fechaentrega = listOf<FechaEntrega>()
    var idpunto_entrega = Long


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_busquedafechasentrega)


        val objetoIntent: Intent = intent
        var idpuntoentrega: String? = objetoIntent.getStringExtra("Idpuntoentrega")

        var idptoentrega: Long = idpuntoentrega!!.toLong()

        swipeRefreshLayout4.setColorSchemeColors(ContextCompat.getColor(this, R.color.color_verde))

        swipeRefreshLayout4.isRefreshing = true

        recycler_fechasentrega.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        getfechasentrega(idptoentrega)

    }


    fun getfechasentrega(idptoentrega: Long) {
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

                        fechaentrega = response.body()!!

                        fechasEntregaAdapter = FechaEntregaAdapter(this@BusquedaFechasEntregaActivity, fechaentrega)
                        recycler_fechasentrega.adapter = fechasEntregaAdapter
                        fechasEntregaAdapter.notifyDataSetChanged()
                    }
                }

            })
    }


}