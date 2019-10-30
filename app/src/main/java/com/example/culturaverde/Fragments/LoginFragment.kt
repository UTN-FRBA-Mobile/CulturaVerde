package com.example.culturaverde.Ui.Login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.culturaverde.Classes.UsuarioGlobal
import com.example.culturaverde.Controllers.UsuarioControlador
import com.example.culturaverde.Models.Usuario
import com.example.culturaverde.R
import kotlinx.android.synthetic.main.fragment_loginmain.*
import com.example.culturaverde.Services.APIConfig
import io.paperdb.Paper
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

        Paper.init(requireContext())

        botonIngreso.setOnClickListener {
            usuarioControlador = APIConfig.getRetrofitClient(requireContext()).
                create(UsuarioControlador::class.java)
            if(nombreUsuario.text.toString()!=""&&contrasena.text.toString()!="") {
                login()

                if (usuario?.rol == "Productor") {
                    val action =
                        LoginFragmentDirections.actionLoginmainfragmentToMenudesplegableProductores()

                    UsuarioGlobal.guardarUsuario(Usuario(usuario!!.id,usuario!!.nombre,usuario!!.apellido,usuario!!.usuario,
                        usuario!!.contraseña, usuario!!.fecha_nacimiento,usuario!!.rol,usuario!!.telefono))

                    findNavController().navigate(action)
                }

                if (usuario?.rol == "Consumidor") {

                    val action =
                        LoginFragmentDirections.actionLoginmainfragmentToMenudesplegableConsumidores()

                    UsuarioGlobal.guardarUsuario(Usuario(usuario!!.id,usuario!!.nombre,usuario!!.apellido,usuario!!.usuario,
                        usuario!!.contraseña, usuario!!.fecha_nacimiento,usuario!!.rol,usuario!!.telefono))

                    findNavController().navigate(action)
                }

            }
            else {
                Toast.makeText(requireContext(), "No se permiten campos vacios", Toast.LENGTH_SHORT).show()
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
                    Log.d("Login error", t.message!!)
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                    usuario = response.body()!!

                }
            })
    }
}
