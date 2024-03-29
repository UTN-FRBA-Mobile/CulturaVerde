package com.example.culturaverde.Activities

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.culturaverde.Controllers.PuntosentregaControlador
import com.example.culturaverde.Models.PuntosEntrega
import com.example.culturaverde.Services.APIConfig
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Response
import android.widget.Toast
import com.example.culturaverde.Classes.ProductorGlobal
import com.example.culturaverde.Models.ProductorMaps
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.esotericsoftware.kryo.util.IntArray




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
        setContentView(com.example.culturaverde.R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(com.example.culturaverde.R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        map.setOnMarkerClickListener(this)
        map.getUiSettings().setZoomControlsEnabled(true)

        setUpMap()

        puntosentregacontrolador = APIConfig.getRetrofitClient(this).create(PuntosentregaControlador::class.java)

        getPuntosEntrega()

        map.setOnInfoWindowClickListener(MyOnInfoWindowClickListener)

    }

    var MyOnInfoWindowClickListener: GoogleMap.OnInfoWindowClickListener =
        GoogleMap.OnInfoWindowClickListener { marker ->

            var id = marker.tag

            ProductorGlobal.guardarProductor(ProductorMaps(id.toString().toLong()))

            startActivity(Intent(this, ResultadoBusqueda::class.java))
        }

    //donde estan los productores
    private fun marketplace (locationproductor: LatLng, razonsocial: String, direccion: String, localidad: String,id:Long ){

        map.addMarker(MarkerOptions().position(locationproductor).title(razonsocial).snippet(direccion.plus(", ").plus(localidad))).setTag(id)
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

            }
            override fun onResponse(call: Call<List<PuntosEntrega>>, response: Response<List<PuntosEntrega>>) {

                puntosentrega = response.body()!!


                for (puntosEntrega in puntosentrega) {
                    var id = puntosEntrega.productor.id!!
                    var location = LatLng(puntosEntrega.latitud,puntosEntrega.longitud)
                    var razonsocial = puntosEntrega.productor.usuario.nombre.toString() + " " + puntosEntrega.productor.usuario.apellido.toString()
                    var direccion = puntosEntrega.direccion
                    var localidad = puntosEntrega.localidad
                    marketplace(location, razonsocial, direccion, localidad,id)
                }

            }

        })
    }

}
