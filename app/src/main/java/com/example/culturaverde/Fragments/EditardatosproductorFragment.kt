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
import com.example.culturaverde.Classes.UsuarioGlobal
import com.example.culturaverde.Models.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat

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

        val nombreProductor = SpannableStringBuilder(UsuarioGlobal.getUsuario()!!.nombre)
        val apellidoProductor = SpannableStringBuilder(UsuarioGlobal.getUsuario()!!.apellido)

        val telefonoProductor = SpannableStringBuilder(UsuarioGlobal.getUsuario()!!.telefono)

        val date = SimpleDateFormat("dd/MM/yyy").format(UsuarioGlobal.getUsuario()!!.fecha_nacimiento)

        val fechaNacimientoProductor= SpannableStringBuilder(date.toString())

        editarNombreProductor.text = nombreProductor
        editarApellidoProductor.text = apellidoProductor
        editarFechaNacProductor.text = fechaNacimientoProductor
        editarTelefonoProductor.text = telefonoProductor

        botonGuardar.setOnClickListener {
            guardarDatosProductor()
        }

        botonLimpiar.setOnClickListener {

            editarNombreProductor.text = SpannableStringBuilder("")
            editarApellidoProductor.text = SpannableStringBuilder("")
            editarFechaNacProductor.text = SpannableStringBuilder("")
            editarTelefonoProductor.text = SpannableStringBuilder("")
        }
    }

    fun guardarDatosProductor(){
        usuarioControlador =
            APIConfig.getRetrofitClient(requireContext()).create(UsuarioControlador::class.java)

        val paramObject = JSONObject()

        if ( editarNombreProductor.text.toString() != "" &&
            editarApellidoProductor.text.toString() != ""&&
            editarFechaNacProductor.text.toString() != ""&&
            editarTelefonoProductor.text.toString() != ""
            ){
            paramObject.put("nombre", editarNombreProductor.text.toString())
            paramObject.put("apellido", editarApellidoProductor.text.toString())


            var fecha_parseada1 = editarFechaNacProductor.text.split("/")

            var dia = fecha_parseada1[0].toString()

            var mes = fecha_parseada1[1].toString()

            var anio = fecha_parseada1[2].toString()

            val date = SimpleDateFormat("dd-MM-yyyy").parse(dia+"-"+mes+"-"+anio)


            paramObject.put("fecha_nacimiento", java.sql.Date(date.getTime()))
            paramObject.put("telefono", editarTelefonoProductor.text.toString())

            usuarioControlador.editarDatosUsuario(paramObject.toString(), UsuarioGlobal.getUsuario()!!.id.toInt())
                .enqueue(object : Callback<Void> {
                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        print(t.message)
                        Log.d("Registro erroneo", t.message!!)
                        Toast.makeText(
                            requireContext(),
                            t.message + "No se ha podido modificar el usuario",
                            Toast.LENGTH_SHORT
                        ).show()
                       /* val action =
                            EditardatosproductorFragmentDirections.actionNavEditardatosproductoresToNavPrincipalproductores()
                        findNavController().navigate(action)

                        */
                    }

                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        Toast.makeText(
                            requireContext(),
                            "Modificación exitosa!",
                            Toast.LENGTH_SHORT
                        ).show()

                        UsuarioGlobal.guardarUsuario(
                            Usuario(UsuarioGlobal.getUsuario()!!.id,editarNombreProductor.text.toString(),editarApellidoProductor.text.toString(),UsuarioGlobal.getUsuario()!!.usuario,
                                UsuarioGlobal.getUsuario()!!.contraseña, java.sql.Date(date.getTime()),UsuarioGlobal.getUsuario()!!!!.rol,editarTelefonoProductor.text.toString())
                        )
                        /*val action =
                            EditardatosproductorFragmentDirections.actionNavEditardatosproductoresToNavPrincipalproductores()
                        findNavController().navigate(action)
                         */
                    }
                })
        }
        else{Toast.makeText(requireContext(),"Debe completar los campos vacios!",Toast.LENGTH_SHORT).show()}
    }
}