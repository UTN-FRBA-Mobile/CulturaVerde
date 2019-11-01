package com.example.culturaverde.Activities

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.culturaverde.R
import kotlinx.android.synthetic.main.fragment_elegirpuntoentregaretiro.*

class ElegirPuntoEntregaRetiroActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var checkboxpuntoentrega: CheckBox
    lateinit var checkboxselectpuntosentrega: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_elegirpuntoentregaretiro)

        checkboxpuntoentrega = findViewById(R.id.checkBoxAcordarConProductor)
        checkboxselectpuntosentrega = findViewById(R.id.checkBoxSelectPuntosEntrega)

        val comboselectpuntosentrega = Spinner(this)

        if (checkboxpuntoentrega.isChecked){
            textoonclick.visibility = View.VISIBLE
        } else {
            textoonclick.visibility = View.INVISIBLE
        }

        val listitemstxt = arrayOf("Ramos mejia", "vicente lopez", "Capital federal")
        val arrayAdapter = ArrayAdapter(this, R.layout.fragment_elegirpuntoentregaretiro, listitemstxt)
        arrayAdapter.setDropDownViewResource(R.layout.fragment_elegirpuntoentregaretiro)
        comboselectpuntosentrega.adapter = arrayAdapter
        comboselectpuntosentrega.onItemSelectedListener = this

        /*
        comboselectpuntosentrega.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(this@ElegirPuntoEntregaRetiroActivity, "Nada", Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                Toast.makeText(this@ElegirPuntoEntregaRetiroActivity, "posicion "+listitemstxt[position] , Toast.LENGTH_SHORT).show()
            }

        }
        */

        /*
        // Initializing a String Array
        val colors = arrayOf("Red","Green","Blue","Yellow","Black","Crimson","Orange")


        // Initializing an ArrayAdapter
        val adapter = ArrayAdapter<String>(
            this, // Context
            R.layout.fragment_elegirpuntoentregaretiro, // Layout
            colors // Array
        )

        // Set the drop down view resource
        adapter.setDropDownViewResource(R.layout.fragment_elegirpuntoentregaretiro)

        // Finally, data bind the spinner object with dapter
        Combopuntoentrega.adapter = adapter


        // Set an on item selected listener for spinner object
        Combopuntoentrega?.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent:AdapterView<*>?, view: View?, position: Int, id: Long){
                // Display the selected item text on text view
                textcombopuntoentrega.text = "Spinner selected : ${parent?.getItemAtPosition(position).toString()}"
            }

            override fun onNothingSelected(parent: AdapterView<*>?){
                // Another interface callback
            }
        }
        */

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
