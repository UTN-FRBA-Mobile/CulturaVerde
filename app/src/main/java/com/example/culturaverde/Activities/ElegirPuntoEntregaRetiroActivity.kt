package com.example.culturaverde.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.culturaverde.R
import kotlinx.android.synthetic.main.fragment_elegirpuntoentregaretiro.*
import kotlinx.android.synthetic.main.fragment_finalizarreserva.*

class ElegirPuntoEntregaRetiroActivity : AppCompatActivity() {

    lateinit var checkboxpuntoentrega: CheckBox
    lateinit var checkboxselectpuntosentrega: CheckBox
    lateinit var comboselectpuntosentrega: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_elegirpuntoentregaretiro)

        val objetoIntent: Intent = intent

        var nombreretira: String? = objetoIntent.getStringExtra("Nombreretira")
        var apellidoretira: String? = objetoIntent.getStringExtra("Apellidoretira")


        checkboxpuntoentrega = findViewById(R.id.checkBoxAcordarConProductor)
        checkboxselectpuntosentrega = findViewById(R.id.checkBoxSelectPuntosEntrega)


        if (checkboxpuntoentrega.isChecked){
            textoonclick.visibility = View.VISIBLE
        } else {
            textoonclick.visibility = View.INVISIBLE
        }

        botonelegirpuntoentrega.setOnClickListener{

            val intent = Intent(this, BusquedaPuntosEntregaActivity::class.java)
            startActivity(intent)

        }
        botonContinuarReserva2.setOnClickListener {

            val intent = Intent(this, ResumenReservasActivity::class.java)
            intent.putExtra("Nombreretira", nombreretira)
            intent.putExtra("Apellidoretira", apellidoretira)

            if (checkboxpuntoentrega.isChecked){
                intent.putExtra("Comoretira", "Acordas el punto de entrega y la fecha con el productor")
            }else
            {
                intent.putExtra("Comoretira", "Retira en Feria A")
            }

            startActivity(intent)

        }

    }


    fun onCheckboxClicked(view: View) {

        if (checkboxpuntoentrega.isChecked){
            textoonclick.visibility = View.VISIBLE
            checkboxselectpuntosentrega.isChecked = false
        } else {
            textoonclick.visibility = View.INVISIBLE
            checkboxselectpuntosentrega.isChecked = true
        }
    }


    fun onCheckboxClickedPuntosEntrega(view: View) {

        if (checkboxselectpuntosentrega.isChecked){
            textoonclick.visibility = View.INVISIBLE
            checkboxpuntoentrega.isChecked = false
        } else {
            textoonclick.visibility = View.VISIBLE
            checkboxpuntoentrega.isChecked = true
        }
    }
}
