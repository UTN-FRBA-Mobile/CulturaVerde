package com.example.culturaverde;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Declaramos un objeto tipo Toolbar y lo instanciamos
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //val host = NavHostFragment.create(R.navigation.nav_graph);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,NavHostFragment.create(R.navigation.nav_graph)).setPrimaryNavigationFragment(NavHostFragment.create(R.navigation.nav_graph)).commit();

    }
 }
