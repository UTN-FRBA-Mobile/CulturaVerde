package com.example.culturaverde.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.culturaverde.R
import kotlinx.android.synthetic.main.fragment_finalizarreserva.*
import android.widget.CheckBox
import com.example.culturaverde.Classes.UsuarioGlobal


class FinalizarReservaActivity : AppCompatActivity() {

    lateinit var checkboxotrapersona: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_finalizarreserva)

        checkboxotrapersona = findViewById(R.id.checkBoxRetiraOtraPersona)


        if (checkboxotrapersona.isChecked) {
            usuarioreserva.setText(UsuarioGlobal.getUsuario().usuario)
            nombrereserva.setText("")
            apellidoreserva.setText("")
            contactoreserva.setText(UsuarioGlobal.getUsuario().telefono)
        } else {
            usuarioreserva.setText(UsuarioGlobal.getUsuario().usuario)
            nombrereserva.setText(UsuarioGlobal.getUsuario().nombre)
            apellidoreserva.setText(UsuarioGlobal.getUsuario().apellido)
            contactoreserva.setText(UsuarioGlobal.getUsuario().telefono)
        }

        botonContinuarReserva.setOnClickListener {

            startActivity(Intent(this, ElegirPuntoEntregaRetiroActivity::class.java))

        }

    }

    fun onCheckboxClicked(view: View) {

        if (checkboxotrapersona.isChecked) {
            usuarioreserva.setText(UsuarioGlobal.getUsuario().usuario)
            nombrereserva.setText("")
            apellidoreserva.setText("")
            contactoreserva.setText(UsuarioGlobal.getUsuario().telefono)
        } else {
            usuarioreserva.setText(UsuarioGlobal.getUsuario().usuario)
            nombrereserva.setText(UsuarioGlobal.getUsuario().nombre)
            apellidoreserva.setText(UsuarioGlobal.getUsuario().apellido)
            contactoreserva.setText(UsuarioGlobal.getUsuario().telefono)

        }
    }

}