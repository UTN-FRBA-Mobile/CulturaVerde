package com.example.culturaverde.Fragments

import android.os.Build
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.culturaverde.Classes.ReservaGlobal
import com.example.culturaverde.Classes.UsuarioGlobal
import com.example.culturaverde.Controllers.ReservasControlador

import com.example.culturaverde.R
import com.example.culturaverde.Services.APIConfig
import com.example.culturaverde.ViewModels.EstadoReservaViewModel
import kotlinx.android.synthetic.main.estado_reserva_fragment.*
import kotlinx.android.synthetic.main.estado_reserva_fragment.view.*
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.concurrent.TimeUnit


class EstadoReservaFragment : Fragment() {

    private lateinit var reservasControlador: ReservasControlador
    private var id_estado:Long?=null

    companion object {
        fun newInstance() = EstadoReservaFragment()
    }

    private lateinit var viewModel: EstadoReservaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.estado_reserva_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EstadoReservaViewModel::class.java)
        // TODO: Use the ViewModel

        view!!.radioButton1.setVisibility(View.GONE)
        view!!.radioButton2.setVisibility(View.GONE)
        view!!.radioButton3.setVisibility(View.GONE)
        view!!.radioButton4.setVisibility(View.GONE)
        view!!.radioButton5.setVisibility(View.GONE)

        var fechaHoy = Date()

        var fechaRetiro:Date

        var dias:Long=0


        if (ReservaGlobal.getReserva().fecha != null) {

            fechaRetiro = ReservaGlobal.getReserva().fecha!!

            val diff:Long = fechaRetiro.getTime() - fechaHoy.getTime()

            dias = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)

        }

        if (UsuarioGlobal.getUsuario().rol == "Consumidor") {

            view!!.radioButton4.setVisibility(View.VISIBLE)

            if (ReservaGlobal.getReserva().fecha != null) {

                if (dias > 2) {

                    view!!.radioButton5.setVisibility(View.VISIBLE)
                }

            }else{

                if(ReservaGlobal.getReserva().forma_retiro=="Acuerda con Productor"){

                    view!!.radioButton5.setVisibility(View.VISIBLE)

                }

            }
        } else {

            when (ReservaGlobal.getReserva().estado_reserva.nombre) {
                "Pendiente" -> {view!!.radioButton2.setVisibility(View.VISIBLE)
                view!!.radioButton3.setVisibility(View.VISIBLE)}
                "En proceso" -> view!!.radioButton3.setVisibility(View.VISIBLE)
                }

            view!!.radioButton4.setVisibility(View.VISIBLE)

            if (ReservaGlobal.getReserva().fecha != null) {

                if (dias > 1) {

                    view!!.radioButton5.setVisibility(View.VISIBLE)
                }

            }else{


                if(ReservaGlobal.getReserva().forma_retiro=="Acuerda con Productor"){


                    view!!.radioButton5.setVisibility(View.VISIBLE)

                }

            }

        }

        radioButtonGroup.setOnCheckedChangeListener{ radioGroup, checkedid ->


            when (checkedid) {
                radioButton1.id -> id_estado=1
                radioButton2.id -> id_estado=2
                radioButton3.id -> id_estado=3
                radioButton4.id -> id_estado=4
                radioButton5.id -> id_estado=5

            }



        }


        buttonGuardarEstado.setOnClickListener{
            guardarEstado(id_estado!!)

        }

        }


    fun guardarEstado(id_estado:Long){


        var id_reserva = ReservaGlobal.getReserva().id

        reservasControlador = APIConfig.getRetrofitClient(requireContext()).create(ReservasControlador::class.java)

        //, UsuarioGlobal.getUsuario().rol
        reservasControlador.modificarEstado(id_reserva,id_estado,UsuarioGlobal.getUsuario().rol)
            .enqueue(object : retrofit2.Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {


                    Toast.makeText(
                        requireContext(),
                        "Ocurrió un error inesperado, intentá nuevamente",
                        Toast.LENGTH_SHORT
                    ).show()

                }

                override fun onResponse(
                    call: Call<String>,
                    response: Response<String>
                ) {


                    Toast.makeText(
                        requireContext(),
                        "Tu reserva " + "#${id_reserva}" + " fue actualizada correctamente!",
                        Toast.LENGTH_SHORT
                    ).show()

                    /*
                  if(UsuarioGlobal.getUsuario().rol=="Consumidor") {
                      view!!.findNavController()
                          .navigate(R.id.action_estadoReservaFragment_to_nav_principalconsumidores)

                      return
                  }

                    view!!.findNavController()
                        .navigate(R.id.action_estadoReservaFragment_to_nav_principalproductores)

                }
*/
                }
            })
    }




}
