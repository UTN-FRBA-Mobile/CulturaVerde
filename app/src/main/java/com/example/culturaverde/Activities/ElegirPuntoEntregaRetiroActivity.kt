package com.example.culturaverde.Activities

import android.os.Bundle
import android.view.View
import android.view.WindowId
import android.widget.*
import android.widget.AdapterView.*
import androidx.appcompat.app.AppCompatActivity
import com.example.culturaverde.R
import kotlinx.android.synthetic.main.fragment_elegirpuntoentregaretiro.*
import org.w3c.dom.Text
import android.widget.AdapterView.OnItemSelectedListener as OnItemSelectedListener1

class ElegirPuntoEntregaRetiroActivity : AppCompatActivity() {

    lateinit var checkboxpuntoentrega: CheckBox
    lateinit var checkboxselectpuntosentrega: CheckBox

    lateinit var comboSpinner : Spinner
    lateinit var textcomboseleccionarpuntoentrega : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_elegirpuntoentregaretiro)

        checkboxpuntoentrega = findViewById(R.id.checkBoxAcordarConProductor)
        checkboxselectpuntosentrega = findViewById(R.id.checkBoxSelectPuntosEntrega)

        if (checkboxpuntoentrega.isChecked){
            textoonclick.visibility = View.VISIBLE
        } else {
            textoonclick.visibility = View.INVISIBLE
        }


        /*textcomboseleccionarpuntoentrega = findViewById(R.id.textcomboseleccionarpuntoentrega)
        comboSpinner = findViewById(R.id.comboseleccionarpuntoentrega)

        val listcombo = arrayOf("ramos", "olivos", "capital")
        comboSpinner.adapter = ArrayAdapter<String>(this, R.layout.fragment_elegirpuntoentregaretiro, listcombo)

        comboSpinner.onItemSelectedListener = object : OnItemSelectedListener1 {
        
            override fun onNothingSelected(p0: AdapterView<*>?) {
                textcomboseleccionarpuntoentrega.text = "Elegir un punto de entrega"
            }

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {

                textcomboseleccionarpuntoentrega.text = listcombo.get(position)

            }


        }*/



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

    //

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
