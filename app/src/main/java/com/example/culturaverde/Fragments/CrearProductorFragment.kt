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
import com.example.culturaverde.ViewModels.CrearProductorViewModel
import kotlinx.android.synthetic.main.fragment_crearusuarioproductor.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import org.json.JSONObject



class CrearProductorFragment : Fragment() {

    private lateinit var crearProductorViewModel: CrearProductorViewModel
    private lateinit var usuarioControlador: UsuarioControlador


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        crearProductorViewModel =
            ViewModelProviders.of(this).get(CrearProductorViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_crearusuarioproductor, container, false)


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

       var date = Date()

        val paramObject = JSONObject()

        paramObject.put("nombre", nombreingresante.text.toString())
        paramObject.put("apellido", ingreseapellido.text.toString())
        paramObject.put("usuario", ingreseemail.text.toString())
        paramObject.put("contraseña", ingresepassword.text.toString())
        paramObject.put("fecha_nacimiento", java.sql.Date(date.getTime()))
        paramObject.put("rol", "Productor")
        paramObject.put("telefono", ingresetelefono.text.toString())


            usuarioControlador.registrar(paramObject.toString(), razonsocial.text.toString())
                .enqueue(object : Callback<Usuario> {
                  override fun onFailure(call: Call<Usuario>, t: Throwable) {
                        print(t.message)

                        Log.d("Registro erroneo", t.message)
                        Toast.makeText(requireContext(), t.message + "Registro Erroneo!!", Toast.LENGTH_SHORT).show()
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
                            CrearProductorFragmentDirections.actionCrearProductorFragmentToLoginmainfragment2()
                        findNavController().navigate(action)
                    }


                })
        }
}