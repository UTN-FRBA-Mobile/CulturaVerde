package com.example.culturaverde.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.culturaverde.R
import kotlinx.android.synthetic.main.fragment_finalizarreserva.*
import android.widget.CheckBox


class FinalizarReservaActivity : AppCompatActivity() {

    lateinit var checkboxotrapersona: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_finalizarreserva)

        checkboxotrapersona = findViewById(R.id.checkBoxRetiraOtraPersona)

        if (checkboxotrapersona.isChecked) {
            usuarioreserva.setText("corbocecilia@gmail.com")
            contactoreserva.setText("123456789")
        } else {
            usuarioreserva.setText("corbocecilia@gmail.com")
            nombrereserva.setText("Cecilia")
            apellidoreserva.setText("Corbo")
            contactoreserva.setText("123456789")
        }

        botonContinuarReserva.setOnClickListener {

            startActivity(Intent(this, ElegirPuntoEntregaRetiroActivity::class.java))

        }

    }

    fun onCheckboxClicked(view: View) {

        if (checkboxotrapersona.isChecked) {
            usuarioreserva.setText("corbocecilia@gmail.com")
            nombrereserva.setText("")
            apellidoreserva.setText("")
            contactoreserva.setText("123456789")
        } else {
            usuarioreserva.setText("corbocecilia@gmail.com")
            nombrereserva.setText("Cecilia")
            apellidoreserva.setText("Corbo")
            contactoreserva.setText("123456789")

        }
    }

}