package com.example.culturaverde.Ui.Editardatosproductor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.culturaverde.Controllers.UsuarioControlador
import com.example.culturaverde.R
import com.example.culturaverde.Services.APIConfig
import com.example.culturaverde.ViewModels.EditardatosproductorViewModel
import kotlinx.android.synthetic.main.fragment_editardatosproductores.*
import org.json.JSONObject
import java.util.*
import android.text.SpannableStringBuilder
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditardatosproductorFragment : Fragment() {

    private lateinit var editardatosproductorViewModel: EditardatosproductorViewModel
    private lateinit var usuarioControlador: UsuarioControlador

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        editardatosproductorViewModel =
            ViewModelProviders.of(this).get(EditardatosproductorViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_editardatosproductores, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nombreProductor = SpannableStringBuilder("Ezequiel")
        val apellidoProductor = SpannableStringBuilder("Bosso")
        val fechaNacimientoProductor= SpannableStringBuilder("1982-09-09")
        val telefonoProductor = SpannableStringBuilder("123456789")
        editarNombreProductor.text = nombreProductor
        editarApellidoProductor.text = apellidoProductor
        editarFechaNacProductor.text = fechaNacimientoProductor
        editarTelefonoProductor.text = telefonoProductor

        botonGuardar.setOnClickListener {
            guardarDatosProductor()
        }

        botonLimpiar.setOnClickListener {
            val nombreProductorN = SpannableStringBuilder("")
            val apellidoProductorN = SpannableStringBuilder("")
            val fechaNacimientoProductorN = SpannableStringBuilder("")
            val telefonoProductorN = SpannableStringBuilder("")
            editarNombreProductor.text = nombreProductorN
            editarApellidoProductor.text = apellidoProductorN
            editarFechaNacProductor.text = fechaNacimientoProductorN
            editarTelefonoProductor.text = telefonoProductorN
        }
    }

    fun guardarDatosProductor(){
        usuarioControlador =
            APIConfig.getRetrofitClient(requireContext()).create(UsuarioControlador::class.java)

        var date = Date()
        val paramObject = JSONObject()

        var arg = arguments
        var id_usuario = arg?.getLong("id_usuario")
        var rol_usuario = arg?.getString("rol_usuario")


        if ( editarNombreProductor.text.toString() != "" &&
            editarApellidoProductor.text.toString() != ""&&
            editarFechaNacProductor.text.toString() != ""&&
            editarTelefonoProductor.text.toString() != ""
            ){
            paramObject.put("nombre", editarNombreProductor.text.toString())
            paramObject.put("apellido", editarApellidoProductor.text.toString())
            paramObject.put("fecha_nacimiento", java.sql.Date(date.getTime()))
            paramObject.put("telefono", editarTelefonoProductor.text.toString())

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
                            EditardatosproductorFragmentDirections.actionNavEditardatosproductoresToNavPrincipalproductores()
                        findNavController().navigate(action)
                    }

                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        Toast.makeText(
                            requireContext(),
                            "Modificación exitosa!",
                            Toast.LENGTH_SHORT
                        ).show()
                        val action =
                            EditardatosproductorFragmentDirections.actionNavEditardatosproductoresToNavPrincipalproductores()
                        findNavController().navigate(action)
                    }
                })
        }
        else{Toast.makeText(requireContext(),"Debe completar los campos vacios!",Toast.LENGTH_SHORT).show()}
    }
}