package com.example.culturaverde.Fragments

import android.os.Bundle
import android.util.Log
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
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_crearusuarioproductor.*
import kotlinx.android.synthetic.main.fragment_loginmain.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


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

    private var disposable: Disposable? = null


    private fun showResult(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
        fun registrarUsuario() {

            usuarioControlador =
                APIConfig.getRetrofitClient(requireContext()).create(UsuarioControlador::class.java)

            var date = Date();
            val formatter = SimpleDateFormat("MMM dd yyyy HH:mma")
            val answer: String = formatter.format(date)

            usuarioControlador.registrar(nombreingresante.text.toString(), ingreseapellido.text.toString(), ingreseemail.text.toString(),
                ingresepassword.text.toString(), date,"Productor", ingresetelefono.text.toString(), razonsocial.text.toString())
                .enqueue(object : Callback<Usuario> {
                  override fun onFailure(call: Call<Usuario>, t: Throwable) {
                        print(t.message)
                        Log.d("Login error", t.message)
                        Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {

                        Toast.makeText(requireContext(), "Todo bien", Toast.LENGTH_SHORT).show()

                    }

                })
/*
               ++++++++++++++++++++++

               this.disposable = usuario?.let {
                    this. usuarioControlador.registrar(it)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ showResult("Successfully created the new user ${usuario}") },
                            { showResult("Failed to create the new user!") })

                }*/

        }
}