package com.example.culturaverde.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.culturaverde.Controllers.UsuarioControlador
import com.example.culturaverde.Models.Usuario
import com.example.culturaverde.R
import com.example.culturaverde.Services.APIConfig
import com.example.culturaverde.ViewModels.CrearConsumidorViewModel
import kotlinx.android.synthetic.main.fragment_crearusuarioconsumidor.*
import kotlinx.android.synthetic.main.fragment_crearusuarioproductor.botonCrearUsuario
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CrearConsumidorFragment: Fragment() {

    private lateinit var crearconsumidorViewModel: CrearConsumidorViewModel
    private lateinit var usuarioControlador: UsuarioControlador

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        crearconsumidorViewModel =
            ViewModelProviders.of(this).get(CrearConsumidorViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_crearusuarioconsumidor, container, false)

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        botonCrearUsuario.setOnClickListener {
            registrarUsuario()
        }
    }

    fun registrarUsuario() {

        usuarioControlador =
            APIConfig.getRetrofitClient(requireContext()).create(UsuarioControlador::class.java)

        var date: Date = Date()

        val paramObject = JSONObject()
        paramObject.put("nombre", nombreConsumidor.text.toString())
        paramObject.put("apellido", apellidoConsumidor.text.toString())
        paramObject.put("usuario", mailConsumidor.text.toString())
        paramObject.put("contraseña", contraseñaConsumidor.text.toString())
        paramObject.put("fecha_nacimiento", java.sql.Date(date.getTime()))
        paramObject.put("rol", "Consumidor")
        paramObject.put("telefono", telefonoConsumidor.text.toString())

        usuarioControlador.registrarConsumidor(paramObject.toString())
            .enqueue(object : Callback<Usuario> {
                override fun onFailure(call: Call<Usuario>, t: Throwable) {
                    print(t.message)
                    Log.d("Registro erroneo", t.message)
                    Toast.makeText(requireContext(), "Registro exitoso!", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                    val sharedPref = activity?.getSharedPreferences(
                        getString(R.string.user_preference_key), Context.MODE_PRIVATE)

                    with (sharedPref?.edit()) {
                        val user = response.body()!!
                        this?.putString("id", user.id.toString())
                        this?.commit()
                    }

                    Toast.makeText(requireContext(), "Registro exitoso!", Toast.LENGTH_SHORT).show()
                    val action =
                        CrearConsumidorFragmentDirections.actionCrearConsumidorFragmentToLoginmainfragment2()
                    findNavController().navigate(action)
                }

            })
    }

}