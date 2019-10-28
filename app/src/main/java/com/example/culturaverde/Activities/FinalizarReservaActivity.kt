package com.example.culturaverde.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.culturaverde.R
import kotlinx.android.synthetic.main.fragment_finalizarreserva.*
import android.widget.CheckBox
import kotlinx.android.synthetic.main.activity_shopping_cart.*


class FinalizarReservaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_finalizarreserva)


        var checkBox = findViewById<CheckBox>(R.id.checkBoxRetiraOtraPersona)
        checkBox?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (checkBoxRetiraOtraPersona.isChecked) {
                usuarioreserva.setText("corbocecilia@gmail.com")
                contactoreserva.setText("123456789")
            } else {
                usuarioreserva.setText("corbocecilia@gmail.com")
                nombrereserva.setText("Cecilia")
                apellidoreserva.setText("Corbo")
                contactoreserva.setText("123456789")
            }
        }

        botonContinuarReserva.setOnClickListener {

            startActivity(Intent(this, ElegirPuntoEntregaRetiroActivity::class.java))
        }

    }

}