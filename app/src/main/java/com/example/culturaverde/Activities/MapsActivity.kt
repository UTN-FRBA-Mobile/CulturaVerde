package com.example.culturaverde.Activities

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.culturaverde.Adapters.ProductAdapter
import com.example.culturaverde.Controllers.PuntosentregaControlador
import com.example.culturaverde.Models.ProductoProductor
import com.example.culturaverde.Models.PuntosEntrega
import com.example.culturaverde.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_resultado_busqueda.*
import retrofit2.Call
import retrofit2.Response

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    companion object{
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onMarkerClick(p0: Marker?)= false

    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    val zoommaps = 10f
    private lateinit var puntosentregacontrolador: PuntosentregaControlador
    private var puntosentrega = listOf<PuntosEntrega>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        map.setOnMarkerClickListener(this)
        map.getUiSettings().setZoomControlsEnabled(true)

        setUpMap()

        val location1 = LatLng(-34.5076863,-58.5273582)
        val razonsocial1 = "Unicenter"
        marketplace(location1, razonsocial1)

        val location2 = LatLng(-34.6339474,-58.6316745)
        val razonsocia2 = "Plaza Oeste"
        marketplace(location2, razonsocia2)

        val location3 = LatLng(-34.5959403,-58.4849074)
        val razonsocia3 = "Facultad UBA Parternal"
        marketplace(location3, razonsocia3)

        val location4 = LatLng(-34.6596738,-58.4702676)
        val razonsocia4 = "Facultad UTN Lugano"
        marketplace(location4, razonsocia4)

        val location5 = LatLng(-34.5986018,-58.4220881)
        val razonsocia5 = "Facultad UTN Medrano"
        marketplace(location5, razonsocia5)

    }

    //donde estan los productores
    private fun marketplace (locationproductor: LatLng, razonsocial: String){

        map.addMarker(MarkerOptions().position(locationproductor).title(razonsocial))
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(locationproductor, zoommaps))

    }

    //Icono rojo de ubicacion
    private fun placeMarker (location: LatLng) {
        val markerOptions = MarkerOptions().position(location)
        map.addMarker(markerOptions)
    }

    private fun setUpMap() {
        //pide permiso para acceder a la ubicacion
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }

        //encuentra nuestra ubicacion
        map.isMyLocationEnabled = true

        //estilo de mapa que muestra.
        map.mapType = GoogleMap.MAP_TYPE_TERRAIN

        //si no encuentra nuestra ubicacion toma la ultima.
        fusedLocationClient.lastLocation.addOnSuccessListener(this){ location ->
            if (location != null) {

                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                placeMarker(currentLatLng)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, zoommaps))

            }
        }
    }

    fun getPuntosEntrega() {

        puntosentregacontrolador.getPuntosentrega().enqueue(object : retrofit2.Callback<List<PuntosEntrega>> {

            override fun onFailure(call: Call<List<PuntosEntrega>>, t: Throwable) {

                print(t.message)
                Log.d("Data error", t.message)
                //Toast.makeText(this@ResultadoBusqueda, t.message, Toast.LENGTH_SHORT).show()

            }
            override fun onResponse(call: Call<List<PuntosEntrega>>, response: Response<List<PuntosEntrega>>) {

                swipeRefreshLayout.isRefreshing = false
                swipeRefreshLayout.isEnabled = false

                puntosentrega = response.body()!!

                //productAdapter = ProductAdapter(this@ResultadoBusqueda, products)

                //products_recyclerview.adapter = productAdapter
                //productAdapter.notifyDataSetChanged()

            }

        })
    }

}
