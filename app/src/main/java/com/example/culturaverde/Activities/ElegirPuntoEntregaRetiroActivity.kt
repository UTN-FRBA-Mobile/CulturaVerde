package com.example.culturaverde.Activities

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.example.culturaverde.R
import kotlinx.android.synthetic.main.fragment_elegirpuntoentregaretiro.*

class ElegirPuntoEntregaRetiroActivity : AppCompatActivity() {

    lateinit var checkboxpuntoentrega: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_elegirpuntoentregaretiro)

        checkboxpuntoentrega = findViewById(R.id.checkBoxAcordarConProductor)

        if (checkboxpuntoentrega.isChecked){
            textoonclick.visibility = View.VISIBLE
        } else {
            textoonclick.visibility = View.INVISIBLE
        }

    }


    fun onCheckboxClicked(view: View) {

        if (checkboxpuntoentrega.isChecked){
            textoonclick.visibility = View.VISIBLE
        } else {
            textoonclick.visibility = View.INVISIBLE
        }
    }

}
