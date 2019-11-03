package com.example.culturaverde.Activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.culturaverde.Adapters.ResumenReservasAdapter
import com.example.culturaverde.Adapters.ShoppingCartAdapter
import com.example.culturaverde.Classes.ResumenReserva
import com.example.culturaverde.Classes.ShoppingCart
import com.example.culturaverde.R
import kotlinx.android.synthetic.main.fragment_resumenreservas.*

class ResumenReservasActivity : AppCompatActivity() {

    lateinit var adapter: ResumenReservasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_resumenreservas)

        adapter = ResumenReservasAdapter(this, ShoppingCart.getCart())
        adapter.notifyDataSetChanged()

        resumenreservas_recyclerView.adapter = adapter
        resumenreservas_recyclerView.layoutManager = LinearLayoutManager(this)


    }


}