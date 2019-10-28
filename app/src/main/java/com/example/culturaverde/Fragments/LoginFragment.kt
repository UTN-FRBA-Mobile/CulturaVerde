package com.example.culturaverde.Ui.Login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.culturaverde.Controllers.UsuarioControlador
import com.example.culturaverde.Models.Usuario
import com.example.culturaverde.R
import kotlinx.android.synthetic.main.fragment_loginmain.*
import com.example.culturaverde.Services.APIConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {

    private lateinit var usuarioControlador: UsuarioControlador
    private var usuario: Usuario? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_loginmain, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        botonIngreso.setOnClickListener {
            usuarioControlador = APIConfig.getRetrofitClient(requireContext()).
                create(UsuarioControlador::class.java)

            login()


            if (usuario?.rol == "Productor") {
                val action =
                    LoginFragmentDirections.actionLoginmainfragmentToMenudesplegableProductores(usuario!!.id,
                        usuario!!.rol)
                findNavController().navigate(action)
            }

            if (usuario?.rol == "Consumidor") {

                val action =
                    LoginFragmentDirections.actionLoginmainfragmentToMenudesplegableConsumidores(usuario!!.id,usuario!!.rol)
                findNavController().navigate(action)
            }
        }


        botonRegistrar.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginmainfragmentToRegistrar()
            findNavController().navigate(action)
        }
    }

    fun login() {

        usuarioControlador.login(nombreUsuario.text.toString(),contrasena.text.toString())
            .enqueue(object : Callback<Usuario> {

                override fun onFailure(call: Call<Usuario>, t: Throwable) {
                    print(t.message)
                    Log.d("Login error", t.message)
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                    usuario = response.body()!!

                }
            })
    }
}
