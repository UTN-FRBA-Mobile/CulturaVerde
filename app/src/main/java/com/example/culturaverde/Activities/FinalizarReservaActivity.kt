package com.example.culturaverde.Activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.culturaverde.R
import kotlinx.android.synthetic.main.fragment_finalizarreserva.*
import android.widget.CheckBox
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.example.culturaverde.Classes.UsuarioGlobal


class FinalizarReservaActivity : AppCompatActivity() {

    lateinit var checkboxotrapersona: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_finalizarreserva)

        val objetoIntent: Intent = intent

        var idproductor: String? = objetoIntent.getStringExtra("Idproductor")


        checkboxotrapersona = findViewById(R.id.checkBoxRetiraOtraPersona)


        if (checkboxotrapersona.isChecked) {
            usuarioreserva.setText(UsuarioGlobal.getUsuario().usuario)
            nombrereserva.isEnabled = true
            nombrereserva.setText("")
            apellidoreserva.isEnabled = true
            apellidoreserva.setText("")
            contactoreserva.setText(UsuarioGlobal.getUsuario().telefono)

        } else {

            usuarioreserva.isEnabled = false
            nombrereserva.isEnabled = false
            apellidoreserva.isEnabled = false
            contactoreserva.isEnabled = false

            usuarioreserva.setText(UsuarioGlobal.getUsuario().usuario)
            nombrereserva.setText(UsuarioGlobal.getUsuario().nombre)
            apellidoreserva.setText(UsuarioGlobal.getUsuario().apellido)
            contactoreserva.setText(UsuarioGlobal.getUsuario().telefono)
        }

        botonContinuarReserva.setOnClickListener {

            var nombreretira = nombrereserva.text.toString()
            var apellidoretira = apellidoreserva.text.toString()
            val intent = Intent(this, ElegirPuntoEntregaRetiroActivity::class.java)

            intent.putExtra("Nombreretira", nombreretira)
            intent.putExtra("Apellidoretira", apellidoretira)
            intent.putExtra("Idproductor", idproductor)
            startActivity(intent)

        }

    }

    fun onCheckboxClicked(view: View) {

        if (checkboxotrapersona.isChecked) {
            usuarioreserva.setText(UsuarioGlobal.getUsuario().usuario)

            nombrereserva.isEnabled = true
            nombrereserva.setText("")
            apellidoreserva.isEnabled = true
            apellidoreserva.setText("")
            contactoreserva.setText(UsuarioGlobal.getUsuario().telefono)

        } else {

            usuarioreserva.isEnabled = false
            nombrereserva.isEnabled = false
            apellidoreserva.isEnabled = false
            contactoreserva.isEnabled = false

            usuarioreserva.setText(UsuarioGlobal.getUsuario().usuario)
            nombrereserva.setText(UsuarioGlobal.getUsuario().nombre)
            apellidoreserva.setText(UsuarioGlobal.getUsuario().apellido)
            contactoreserva.setText(UsuarioGlobal.getUsuario().telefono)
        }
    }

}