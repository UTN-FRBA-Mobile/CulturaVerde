package com.example.culturaverde.Activities

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.media.session.MediaButtonReceiver.handleIntent
import com.example.culturaverde.R

class SearchResultsActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_busqueda)
        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        handleIntent(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menudesplegable_consumidores, menu)

        //Associate searchable configuration with the SearchView


        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(
            searchManager.getSearchableInfo(componentName)
        )

        return true

    }

    private fun handleIntent(intent: Intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            var query  = intent.getStringExtra(SearchManager.QUERY)

        }
    }
}