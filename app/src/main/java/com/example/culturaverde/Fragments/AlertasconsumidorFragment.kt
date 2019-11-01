package com.example.culturaverde.Ui.Alertasconsumidor

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.culturaverde.Classes.UsuarioGlobal
import com.example.culturaverde.Controllers.AlertasControlador
import com.example.culturaverde.Models.AlertaUsuario
import com.example.culturaverde.Models.Usuario
import com.example.culturaverde.R
import com.example.culturaverde.Services.APIConfig
import com.example.culturaverde.ViewModels.AlertasconsumidorViewModel
import kotlinx.android.synthetic.main.fragment_alertasconsumidores.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.stream.Collector

class AlertasconsumidorFragment : Fragment() {

    private lateinit var alertasconsumidorViewModel: AlertasconsumidorViewModel
    private lateinit var alertasControlador: AlertasControlador
    val usuario:Usuario=UsuarioGlobal.getUsuario()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        alertasconsumidorViewModel =
            ViewModelProviders.of(this).get(AlertasconsumidorViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_alertasconsumidores, container, false)

        return root
    }

    override fun onActivityCreated( savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val usuario:Usuario=UsuarioGlobal.getUsuario()
        var alertas:List<AlertaUsuario>

        alertasControlador =
            APIConfig.getRetrofitClient(requireContext()).create(AlertasControlador::class.java)


        alertasControlador.obtenerConfiguracionAlertas(UsuarioGlobal.getUsuario().id)
            .enqueue(object : Callback<List<AlertaUsuario>> {
                override fun onFailure(call: Call<List<AlertaUsuario>>, t: Throwable) {
                    print(t.message)
                    Log.d("Registro erroneo", t.message!!)
                    Toast.makeText(requireContext(),t.message + "Ocurrió un eror inespeado, intente nuevamente",
                        Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<List<AlertaUsuario>>,
                    response: Response<List<AlertaUsuario>>) {

                    if(response!=null) {
                        alertas = response.body()!!
                        if(alertas.any { a-> a.alerta.id.toInt() == 1}){

                    switch1.isChecked=true
                        }


                    }


                    }


            })

        botonGuardarAlertas.setOnClickListener{

            val alertas_a_guardar:ArrayList<JSONObject> = arrayListOf()

            var paramObject = JSONObject()

            paramObject.put("alertaNombre", "Actualización de reservas")
            paramObject.put("frecuencia", "En el momento")

            alertas_a_guardar.add(paramObject)

            alertasControlador.guardarConfiguracionAlertas(UsuarioGlobal.getUsuario().id,alertas_a_guardar.toString())
                .enqueue(object : Callback<Void> {
                    override fun onFailure(call: Call<Void>, t: Throwable) {

                        Toast.makeText(requireContext(),t.message + "Ocurrió un eror inespeado, intente nuevamente",
                            Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(
                        call: Call<Void>,
                        response: Response<Void>) {

                        Toast.makeText(requireContext(),"Alerta guardada correctamente",
                            Toast.LENGTH_SHORT).show()


                    }


                })


        }
    }
}