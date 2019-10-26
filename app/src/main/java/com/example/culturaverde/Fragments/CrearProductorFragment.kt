package com.example.culturaverde.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.culturaverde.Controllers.UsuarioControlador
import com.example.culturaverde.Models.Usuario
import com.example.culturaverde.R
import com.example.culturaverde.Services.APIConfig
import com.example.culturaverde.ViewModels.CrearProductorViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_crearusuarioproductor.*



class CrearProductorFragment : Fragment() {

    private lateinit var crearProductorViewModel: CrearProductorViewModel
    private lateinit var usuarioControlador: UsuarioControlador
    private lateinit var usuario: Usuario


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
            usuarioControlador =
                APIConfig.getRetrofitClient(requireContext()).create(UsuarioControlador::class.java)

            registrarUsuario()

        }
        /* if (usuario?.rol == "Productor") {
                val action =
                    LoginFragmentDirections.actionLoginmainfragmentToMenudesplegableProductores()
                findNavController().navigate(action)
            }

            if (usuario?.rol == "Consumidor") {

                val action =
                    LoginFragmentDirections.actionLoginmainfragmentToMenudesplegableConsumidores()
                findNavController().navigate(action)
            }
        }*/

    }

    private var disposable: Disposable? = null


    private fun showResult(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
        fun registrarUsuario() {


            usuario.nombre = nombreingresante.text.toString()
            usuario.apellido = ingreseapellido.text.toString()
            usuario.telefono = ingresetelefono.text.toString()
            usuario.rol = "Productor"
            usuario.usuario = ingreseemail.text.toString()
            usuario.contraseña = ingresepassword.text.toString()

                this.disposable = this. usuarioControlador.registrar(usuario)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ showResult("Successfully created the new user ${usuario}") },
                { showResult("Failed to create the new user!") })
        }
}