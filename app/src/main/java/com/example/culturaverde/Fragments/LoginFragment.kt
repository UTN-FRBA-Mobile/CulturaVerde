package com.example.culturaverde.Ui.Login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.culturaverde.Classes.ProductorGlobal
import com.example.culturaverde.Classes.UsuarioGlobal
import com.example.culturaverde.Controllers.UsuarioControlador
import com.example.culturaverde.Models.ProductorMaps
import com.example.culturaverde.Models.Usuario
import com.example.culturaverde.R
import kotlinx.android.synthetic.main.fragment_loginmain.*
import com.example.culturaverde.Services.APIConfig
import com.example.culturaverde.Services.MyFirebaseInstanceService
import com.google.firebase.iid.FirebaseInstanceId
import io.paperdb.Paper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {

    private lateinit var usuarioControlador: UsuarioControlador

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

            if(nombreUsuario.text.toString()!=""&&contrasena.text.toString()!="") {

                login(nombreUsuario.text.toString(), contrasena.text.toString())


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

    fun login(user:String,contraseña:String) {

        usuarioControlador = APIConfig.getRetrofitClient(requireContext()).
            create(UsuarioControlador::class.java)

        usuarioControlador.login(user,contraseña)
            .enqueue(object : Callback<Usuario> {

                override fun onFailure(call: Call<Usuario>, t: Throwable) {
                    Toast.makeText(requireContext(), "Usuario inexistente o contraseña incorrecta", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {

                    var usuario = response.body()!!

                    if (usuario!!.rol == "Productor") {
                        val action =
                            LoginFragmentDirections.actionLoginmainfragmentToMenudesplegableProductores()

                        UsuarioGlobal.guardarUsuario(Usuario(usuario!!.id,usuario!!.nombre,usuario!!.apellido,usuario!!.usuario,
                            usuario!!.contraseña, usuario!!.fecha_nacimiento,usuario!!.rol,usuario!!.telefono))

                        findNavController().navigate(action)
                    }

                    if (usuario!!.rol == "Consumidor") {

                        val action =
                            LoginFragmentDirections.actionLoginmainfragmentToMenudesplegableConsumidores()

                        ProductorGlobal.guardarProductor(ProductorMaps(0))

                        UsuarioGlobal.guardarUsuario(Usuario(usuario!!.id,usuario!!.nombre,usuario!!.apellido,usuario!!.usuario,
                            usuario!!.contraseña, usuario!!.fecha_nacimiento,usuario!!.rol,usuario!!.telefono))

                        findNavController().navigate(action)
                    }
                    val sharedPref = activity?.getSharedPreferences(
                        getString(R.string.user_preference_key), Context.MODE_PRIVATE)
                    val deviceToken = sharedPref?.getString("deviceToken", FirebaseInstanceId.getInstance().token)
                    Log.d("LOGIN FRAGMENT", "DEVICE TOKEN $deviceToken")
                    MyFirebaseInstanceService.sendRegistrationToServer(deviceToken)
                }
            })
    }
}
