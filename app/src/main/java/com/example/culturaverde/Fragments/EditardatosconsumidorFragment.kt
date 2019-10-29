package com.example.culturaverde.Ui.Editardatosconsumidor

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.culturaverde.Controllers.UsuarioControlador
import com.example.culturaverde.R
import com.example.culturaverde.Services.APIConfig
import com.example.culturaverde.ViewModels.EditardatosconsumidorViewModel
import kotlinx.android.synthetic.main.fragment_editardatosconsumidores.*
import kotlinx.android.synthetic.main.fragment_editardatosproductores.botonGuardar
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class EditardatosconsumidorFragment : Fragment() {

    private lateinit var editardatosconsumidorViewModel: EditardatosconsumidorViewModel
    private lateinit var usuarioControlador: UsuarioControlador


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        editardatosconsumidorViewModel =
            ViewModelProviders.of(this).get(EditardatosconsumidorViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_editardatosconsumidores, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nombreConsumidor = SpannableStringBuilder("Ezequiel")
        val apellidoConsumidor = SpannableStringBuilder("Bosso")
        val fechaNacimientoConsumidor = SpannableStringBuilder("1982-09-09")
        val telefonoConsumidor = SpannableStringBuilder("123456789")
        nombreUsuario.text = nombreConsumidor
        apellidoUsuario.text = apellidoConsumidor
        fechaNac.text = fechaNacimientoConsumidor
        telefono.text = telefonoConsumidor

        botonGuardar.setOnClickListener {
            guardarDatosConsumidor()
        }
        botonLimpiar.setOnClickListener {
            val nombreConsumidorN = SpannableStringBuilder("")
            val apellidoConsumidorN = SpannableStringBuilder("")
            val fechaNacimientoConsumidorN = SpannableStringBuilder("")
            val telefonoConsumidorN = SpannableStringBuilder("")
            nombreUsuario.text = nombreConsumidorN
            apellidoUsuario.text = apellidoConsumidorN
            fechaNac.text = fechaNacimientoConsumidorN
            telefono.text = telefonoConsumidorN
        }
    }

    fun guardarDatosConsumidor(){

        usuarioControlador =
            APIConfig.getRetrofitClient(requireContext()).create(UsuarioControlador::class.java)

        var date = Date()
        val paramObject = JSONObject()
        if ( nombreUsuario.text.toString() != "" &&
            apellidoUsuario.text.toString() != "" &&
            fechaNac.text.toString() != "" &&
            telefono.text.toString() != ""
        ) {
            paramObject.put("nombre", nombreUsuario.text.toString())
            paramObject.put("apellido", apellidoUsuario.text.toString())
            paramObject.put("fecha_nacimiento", java.sql.Date(date.getTime()))
            paramObject.put("telefono", telefono.text.toString())

            usuarioControlador.editarDatosUsuario(paramObject.toString(), 1)
                .enqueue(object : Callback<Void> {
                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        print(t.message)
                        Log.d("Registro erroneo", t.message!!)
                        Toast.makeText(
                            requireContext(),
                            t.message + "No se ha podido modificar el usuario",
                            Toast.LENGTH_SHORT
                        ).show()
                        val action =
                            EditardatosconsumidorFragmentDirections.actionNavEditardatosconsumidoresToNavPrincipalconsumidores()
                        findNavController().navigate(action)
                    }

                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        Toast.makeText(
                            requireContext(),
                            "Modificación exitosa!",
                            Toast.LENGTH_SHORT
                        ).show()
                        val action =
                            EditardatosconsumidorFragmentDirections.actionNavEditardatosconsumidoresToNavPrincipalconsumidores()
                        findNavController().navigate(action)
                    }
                })
        }
        else{Toast.makeText(requireContext(),"Debe completar los campos vacios!",Toast.LENGTH_SHORT).show()}
    }
}