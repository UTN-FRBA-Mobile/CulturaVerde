package com.example.culturaverde.Fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import com.example.culturaverde.Classes.ProductoGlobal
import com.example.culturaverde.Classes.UsuarioGlobal
import com.example.culturaverde.Controllers.OfertasControlador

import com.example.culturaverde.R
import com.example.culturaverde.Services.APIConfig
import com.example.culturaverde.ViewModels.OfertasViewModel
import kotlinx.android.synthetic.main.ofertas_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OfertasFragment : Fragment() {

    companion object {
        fun newInstance() = OfertasFragment()
    }

    private lateinit var viewModel: OfertasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.ofertas_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(OfertasViewModel::class.java)
        // TODO: Use the ViewModel

        precio_actual.text = "$" + ProductoGlobal.getProducto().precio.toString()

        porcentaje.isEnabled = false

        if (ProductoGlobal.getProducto().oferta != null) {

            if (ProductoGlobal.getProducto().oferta!!.activo == true) {
                checkBoxOferta.isChecked = true
                porcentaje.isEnabled = true

                porcentaje.text =
                    SpannableStringBuilder(ProductoGlobal.getProducto().oferta!!.porcentaje.toString())

                nuevo_precio.text =
                    "$" + (ProductoGlobal.getProducto().precio!! - ((ProductoGlobal.getProducto().oferta!!.porcentaje!!.toInt() * ProductoGlobal.getProducto().precio!!) / 100)).toString()

            }else {

                porcentaje.isEnabled = false

            }
        }

        checkBoxOferta.setOnClickListener {

            if (checkBoxOferta.isChecked) {

                porcentaje.isEnabled = true
                return@setOnClickListener
            }
                porcentaje.isEnabled = false
        }

        porcentaje.setOnClickListener {

            nuevo_precio.text =
                "$" + (ProductoGlobal.getProducto().precio!! - ((porcentaje.text.toString().toInt() * ProductoGlobal.getProducto().precio!!) / 100)).toString()

        }


        buttonGuardarOferta.setOnClickListener {


            var activo: Boolean = false

            var id_oferta: Long = 0

            if (checkBoxOferta.isChecked) {

                activo = true

            }

            if (ProductoGlobal.getProducto().oferta != null) {

                id_oferta = ProductoGlobal.getProducto().oferta!!.id!!

            }

            var ofertaControlador: OfertasControlador =
                APIConfig.getRetrofitClient(requireContext()).create(OfertasControlador::class.java)

            if ((ProductoGlobal.getProducto().oferta!=null && !porcentaje.text.isEmpty())||
                (ProductoGlobal.getProducto().oferta==null&& checkBoxOferta.isChecked && !porcentaje.text.isEmpty())) {
                ofertaControlador.guardarOferta(
                    ProductoGlobal.getProducto().id!!,
                    porcentaje.text.toString().toInt(),
                    activo,
                    id_oferta
                ).enqueue(object : Callback<Void> {
                    override fun onFailure(call: Call<Void>, t: Throwable) {

                        Toast.makeText(
                            requireContext(),
                            "Ocurrió un problema inesperado, intente nuevamente",
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        Toast.makeText(
                            requireContext(),
                            "Ofertada guardada correctamente!",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                })
            }else
            {

                Toast.makeText(
                    requireContext(),
                    "Tenés que validar la oferta y completar el porcentaje de descuento!",
                    Toast.LENGTH_SHORT
                ).show()

            }

        }
    }
}
