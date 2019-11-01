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

        checkboxpuntoentrega = findViewById(R.id.checkBoxAcordarConProductor)
        checkboxselectpuntosentrega = findViewById(R.id.checkBoxSelectPuntosEntrega)
        comboselectpuntosentrega  = findViewById(R.id.Combopuntoentrega)

        if (checkboxpuntoentrega.isChecked){
            textoonclick.visibility = View.VISIBLE
        } else {
            textoonclick.visibility = View.INVISIBLE
        }

        val listitemstxt = arrayOf("Ramos mejia", "vicente lopez", "Capital federal")
        val arrayAdapter = ArrayAdapter(this, R.layout.fragment_elegirpuntoentregaretiro, listitemstxt)
        arrayAdapter.setDropDownViewResource(R.layout.fragment_elegirpuntoentregaretiro)
/*
        comboselectpuntosentrega.adapter = arrayAdapter

        comboselectpuntosentrega.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(this@ElegirPuntoEntregaRetiroActivity, "Nada", Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                Toast.makeText(this@ElegirPuntoEntregaRetiroActivity, "posicion "+listitemstxt[position] , Toast.LENGTH_SHORT).show()
            }

        }
*/
        botonContinuarReserva2.setOnClickListener {

            startActivity(Intent(this, ResumenReservasActivity::class.java))

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
