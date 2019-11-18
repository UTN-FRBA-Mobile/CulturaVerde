package com.example.culturaverde.Activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.culturaverde.R
import kotlinx.android.synthetic.main.fragment_elegirpuntoentregaretiro.*
import kotlinx.android.synthetic.main.fragment_finalizarreserva.*

class ElegirPuntoEntregaRetiroActivity : AppCompatActivity() {

    lateinit var checkboxpuntoentrega: CheckBox
    lateinit var checkboxselectpuntosentrega: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_elegirpuntoentregaretiro)

        val objetoIntent: Intent = intent


        var nombreretira: String? = objetoIntent.getStringExtra("Nombreretira")
        var apellidoretira: String? = objetoIntent.getStringExtra("Apellidoretira")
        var idproductor: String? = objetoIntent.getStringExtra("Idproductor")

        var idpuntoentrega: String? = objetoIntent.getStringExtra("Idpuntoentrega")
        var direccioncompleta: String? = objetoIntent.getStringExtra("Direccioncompleta")

        var fechaentregaid: String? = objetoIntent.getStringExtra("Fechaentregaid")
        var fechaentrega: String? = objetoIntent.getStringExtra("Fechaentrega")

        var horariodesde: String? = objetoIntent.getStringExtra("Horariodesde")
        var horariohasta: String? = objetoIntent.getStringExtra("Horariohasta")

        checkboxpuntoentrega = findViewById(R.id.checkBoxAcordarConProductor)
        checkboxselectpuntosentrega = findViewById(R.id.checkBoxSelectPuntosEntrega)


        if (direccioncompleta == null && fechaentrega == null)
        {
            checkboxpuntoentrega.isChecked = true
            textoonclick.visibility = View.VISIBLE
            botonelegirpuntoentrega.visibility = View.INVISIBLE
            textpuntoentrega.visibility = View.INVISIBLE
            textfechapuntoentrega.visibility = View.INVISIBLE
            botonelegirfechaentrega.visibility = View.INVISIBLE
            checkboxselectpuntosentrega.isChecked = false

        }else
        {
            checkboxselectpuntosentrega.isChecked = true
            botonelegirpuntoentrega.visibility = View.VISIBLE
            textpuntoentrega.visibility = View.VISIBLE
            textpuntoentrega.setText(direccioncompleta)
            botonelegirfechaentrega.visibility = View.VISIBLE
            textfechapuntoentrega.visibility = View.VISIBLE
            textfechapuntoentrega.setText(fechaentrega)

            checkboxpuntoentrega.isChecked = false
            textoonclick.visibility = View.INVISIBLE

        }

        botonelegirpuntoentrega.setOnClickListener{

            if (idproductor == null)
            {
                Toast.makeText(this, "No se pudo obtener el id del productor. Intente en unos minutos", LENGTH_LONG).show()
            }else {
                val intent = Intent(this, BusquedaPuntosEntregaActivity::class.java)
                intent.putExtra("Idproductor", idproductor)
                intent.putExtra("Nombreretira", nombreretira)
                intent.putExtra("Apellidoretira", apellidoretira)
                intent.putExtra("Idpuntoentrega", idpuntoentrega)
                intent.putExtra("Direccioncompleta", direccioncompleta)
                intent.putExtra("Fechaentregaid", fechaentregaid)
                intent.putExtra("Fechaentrega", fechaentrega)
                intent.putExtra("Horariodesde", horariodesde)
                intent.putExtra("Horariohasta", horariohasta)

                startActivity(intent)
            }
        }

        botonelegirfechaentrega.setOnClickListener {

            if (idpuntoentrega == null)
            {
             Toast.makeText(this, "Primero debe elegir un punto de entrega", LENGTH_LONG).show()
            }else {
                val intent = Intent(this, BusquedaFechasEntregaActivity::class.java)
                intent.putExtra("Idproductor", idproductor)
                intent.putExtra("Nombreretira", nombreretira)
                intent.putExtra("Apellidoretira", apellidoretira)
                intent.putExtra("Idpuntoentrega", idpuntoentrega)
                intent.putExtra("Direccioncompleta", direccioncompleta)
                intent.putExtra("Fechaentregaid", fechaentregaid)
                intent.putExtra("Fechaentrega", fechaentrega)
                intent.putExtra("Horariodesde", horariodesde)
                intent.putExtra("Horariohasta", horariohasta)

                startActivity(intent)
            }
        }

        botonContinuarReserva2.setOnClickListener {

            val intent = Intent(this, ResumenReservasActivity::class.java)
            intent.putExtra("Nombreretira", nombreretira)
            intent.putExtra("Apellidoretira", apellidoretira)
            intent.putExtra("id_productor", objetoIntent.getStringExtra("Idproductor"))

            if (checkboxpuntoentrega.isChecked){
                intent.putExtra("Opcion", "1")
                intent.putExtra("Comoretira", "Acordas el punto de entrega y la fecha con el productor")
                startActivity(intent)
            }else {
                if (direccioncompleta == null) {
                    Toast.makeText(this, "Primero debe elegir un punto de entrega", LENGTH_LONG)
                        .show()
                } else
                {
                    if (fechaentrega == null)
                    {
                        Toast.makeText(this, "Debe elegir una fecha de entrega para el punto de entrega $direccioncompleta", LENGTH_LONG).show()
                    }else {
                        var direccion: String? = direccioncompleta
                        var fechaentrega: String? = fechaentrega
                        var horarioentregadesde: String? = horariodesde
                        var horarioentregahasta: String? = horariohasta

                        intent.putExtra("Opcion", "2")
                        intent.putExtra("id_punto_entrega", objetoIntent.getStringExtra("Idpuntoentrega") )
                        intent.putExtra("Comoretira", "Por el punto de entrega elegido:")
                        intent.putExtra("Direccion", direccion)
                        intent.putExtra("Fechaentrega", fechaentrega)
                        intent.putExtra("Horario", "Horario de Atención: desde las $horarioentregadesde hasta las $horarioentregahasta")
                        startActivity(intent)
                    }
                }
            }

        }

    }


    fun onCheckboxClicked(view: View) {

        if (checkboxpuntoentrega.isChecked){
            textoonclick.visibility = View.VISIBLE
            botonelegirpuntoentrega.visibility = View.INVISIBLE
            textpuntoentrega.visibility = View.INVISIBLE
            textfechapuntoentrega.visibility = View.INVISIBLE
            botonelegirfechaentrega.visibility = View.INVISIBLE
            checkboxselectpuntosentrega.isChecked = false
        } else {
            textoonclick.visibility = View.INVISIBLE
            botonelegirpuntoentrega.visibility = View.VISIBLE
            checkboxselectpuntosentrega.isChecked = true
            textpuntoentrega.visibility = View.VISIBLE
            textfechapuntoentrega.visibility = View.VISIBLE
            botonelegirfechaentrega.visibility = View.VISIBLE
        }
    }


    fun onCheckboxClickedPuntosEntrega(view: View) {

        if (checkboxselectpuntosentrega.isChecked){
            textoonclick.visibility = View.INVISIBLE
            checkboxpuntoentrega.isChecked = false
            botonelegirpuntoentrega.visibility = View.VISIBLE
            textpuntoentrega.visibility = View.VISIBLE
            textfechapuntoentrega.visibility = View.VISIBLE
            botonelegirfechaentrega.visibility = View.VISIBLE
        } else {
            textoonclick.visibility = View.VISIBLE
            checkboxpuntoentrega.isChecked = true
            botonelegirpuntoentrega.visibility = View.INVISIBLE
            textpuntoentrega.visibility = View.INVISIBLE
            textfechapuntoentrega.visibility = View.INVISIBLE
            botonelegirfechaentrega.visibility = View.INVISIBLE
        }
    }
}
