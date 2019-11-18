package com.example.culturaverde.Ui.MenuDesplegable

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.widget.Toast
import com.example.culturaverde.R
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import com.example.culturaverde.Activities.ShoppingCartActivity
import com.example.culturaverde.Classes.UsuarioGlobal
import kotlinx.android.synthetic.main.nav_header_menudesplegable_consumidores.*
import android.view.View
import com.example.culturaverde.Activities.ResultadoBusqueda
import com.example.culturaverde.Classes.CategoriaProductoGlobal
import com.example.culturaverde.Models.Producto

class menudesplegable_consumidores : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menudesplegable_consumidores)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_principalconsumidores
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menudesplegable_consumidores, menu)
        navHeaderConsumidor.text = UsuarioGlobal.getUsuario()!!.usuario

        //Associate searchable configuration with the SearchView


        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView
        searchView.setMaxWidth(Integer.MAX_VALUE)
        searchView.queryHint = "Buscar productos y productores.."
        searchView.setOnQueryTextListener(object :  SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {

                CategoriaProductoGlobal.guardarProducto(Producto(0,"jej",query))
              startActivity(Intent(this@menudesplegable_consumidores,ResultadoBusqueda::class.java))

                return false
            }

        })

        return true

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}
