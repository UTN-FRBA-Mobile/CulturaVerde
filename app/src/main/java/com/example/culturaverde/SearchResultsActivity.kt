package com.example.culturaverde

import android.app.Activity
import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import androidx.media.session.MediaButtonReceiver.handleIntent

class SearchResultsActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {

        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            //use the query to search your data somehow

        }
    }
}