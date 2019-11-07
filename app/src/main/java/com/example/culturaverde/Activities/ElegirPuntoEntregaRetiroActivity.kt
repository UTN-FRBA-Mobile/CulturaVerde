package com.example.culturaverde.Activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.culturaverde.R
import kotlinx.android.synthetic.main.fragment_elegirpuntoentregaretiro.*
import kotlinx.android.synthetic.main.fragment_finalizarreserva.*

class ElegirPuntoEntregaRetiroActivity : AppCompatActivity() {

    lateinit var checkboxpuntoentrega: CheckBox
    lateinit var checkboxselectpuntosentrega: CheckBox
    lateinit var comboselectpuntosentrega: Spinner

    @SuppressLint("ResourceType")

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_elegirpuntoentregaretiro)

        val objetoIntent: Intent = intent

        var nombreretira: String? = objetoIntent.getStringExtra("Nombreretira")
        var apellidoretira: String? = objetoIntent.getStringExtra("Apellidoretira")
        var idproductor: String? = objetoIntent.getStringExtra("Idproductor")

        checkboxpuntoentrega = findViewById(R.id.checkBoxAcordarConProductor)
        checkboxselectpuntosentrega = findViewById(R.id.checkBoxSelectPuntosEntrega)


        if (checkboxpuntoentrega.isChecked){
            textoonclick.visibility = View.VISIBLE
            botonelegirpuntoentrega.visibility = View.INVISIBLE
            textpuntoentrega.visibility = View.INVISIBLE
            textfechapuntoentrega.visibility = View.INVISIBLE
            botonelegirfechaentrega.visibility = View.INVISIBLE
        } else {
            textoonclick.visibility = View.INVISIBLE
            botonelegirpuntoentrega.visibility = View.VISIBLE
            textpuntoentrega.visibility = View.VISIBLE
            textfechapuntoentrega.visibility = View.VISIBLE
            botonelegirfechaentrega.visibility = View.VISIBLE
        }

        botonelegirpuntoentrega.setOnClickListener{

            val intent = Intent(this, BusquedaPuntosEntregaActivity::class.java)
            intent.putExtra("Idproductor", idproductor)

            startActivity(intent)

        }

        botonelegirfechaentrega.setOnClickListener {

            val intent = Intent(this, BusquedaFechasEntregaActivity::class.java)
            intent.putExtra("Idpuntoentrega", "2")

            startActivity(intent)
        }

        botonContinuarReserva2.setOnClickListener {

            val intent = Intent(this, ResumenReservasActivity::class.java)
            intent.putExtra("Nombreretira", nombreretira)
            intent.putExtra("Apellidoretira", apellidoretira)

            if (checkboxpuntoentrega.isChecked){
                intent.putExtra("Opcion", "1")
                intent.putExtra("Comoretira", "Acordas el punto de entrega y la fecha con el productor")
            }else
            {
                var direccion: String = "Av. Córdoba 3555, C1188. AAB, Buenos Aires"
                var fechaentrega: String = "24/11/2020"
                var horarioentregadesde: String = "06:00"
                var horarioentregahasta: String = "18:00"

                intent.putExtra("Opcion", "2")
                intent.putExtra("Comoretira", "Por el punto de entrega elegido:")
                intent.putExtra("Direccion", direccion)
                intent.putExtra("fechaentrega", "A partir del dia $fechaentrega")
                intent.putExtra("horario", "Horario de Atención: desde las $horarioentregadesde hasta las $horarioentregahasta")
            }

            startActivity(intent)

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
