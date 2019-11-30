package com.example.culturaverde.Fragments

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.culturaverde.Adapters.ModificarProductoAdapter
import com.example.culturaverde.Classes.ProductoGlobal
import com.example.culturaverde.Controllers.ProductosControlador
import com.example.culturaverde.Models.ProductoProductor
import com.example.culturaverde.R
import com.example.culturaverde.Services.APIConfig
import com.example.culturaverde.ViewModels.ProductoModificarViewModel
import kotlinx.android.synthetic.main.producto_modificar_fragment.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.pm.PackageManager
import android.provider.MediaStore
import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.example.culturaverde.Models.Photo
import kotlinx.android.synthetic.main.productos_productor_fragment.*

class ProductoModificarFragment : Fragment() {



    companion object {
        fun newInstance() = ProductoModificarFragment()
    }

    private lateinit var viewModel: ProductoModificarViewModel
    private lateinit var modificarProductoAdapter: ModificarProductoAdapter
    private lateinit var productosControlador: ProductosControlador
    val product: ProductoProductor=ProductoGlobal.getProducto()
    private val PERMISSION_CODE = 1000
    private val IMAGE_CAPTURE_CODE = 1001
    var image_uri: Uri? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.producto_modificar_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProductoModificarViewModel::class.java)
        // TODO: Use the ViewModel


        visorFotosProducto.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        modificarProductoAdapter = ModificarProductoAdapter(requireContext(), product.imagenes)

        visorFotosProducto.adapter = modificarProductoAdapter
        modificarProductoAdapter.notifyDataSetChanged()

        modificarPrecioProducto.text = SpannableStringBuilder(product.precio.toString())
        modificarStockProducto.text  = SpannableStringBuilder(product.stock.toString())
        modificarTituloProducto.text = SpannableStringBuilder(product.titulo.toString())
        modificarDescripcion.text = SpannableStringBuilder(product.descripcion.toString())

        botonModificarProducto.setOnClickListener{

            guardarDatosProducto()
        }

        botonModificarFotos.setOnClickListener{

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_DENIED ||
                    ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED){
                    //permission was not enabled
                    val permission = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    //show popup to request permission
                    requestPermissions(permission, PERMISSION_CODE)
                }
                else{
                    //permission already granted
                    openCamera()
                }
            }
            else{
                //system os is < marshmallow
                openCamera()
            }



        }
    }

    private fun openCamera() {
        val context: Context = requireContext()
        val cr: ContentResolver = requireContext().contentResolver
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
        image_uri = cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        //camera intent
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        //called when user presses ALLOW or DENY from Permission Request Popup
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup was granted
                    openCamera()
                }
                else{
                    //permission from popup was denied
                    Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
      //  called when image was captured from camera intent
        if (resultCode == Activity.RESULT_OK){
            //set image captured to image view
            visor.setImageURI(image_uri)
        }
    }


    fun guardarDatosProducto(){

        productosControlador =
            APIConfig.getRetrofitClient(requireContext()).create(ProductosControlador::class.java)

        val paramObject = JSONObject()
        if ( modificarPrecioProducto.text.toString() != "" &&
            modificarStockProducto.text.toString() != "" &&
            modificarTituloProducto.text.toString()!= "" &&
            modificarDescripcion.text.toString()!= ""
        ) {
            paramObject.put("precio", modificarPrecioProducto.text.toString())
            paramObject.put("stock", modificarStockProducto.text.toString())
            paramObject.put("titulo", modificarTituloProducto.text.toString())
            paramObject.put("descripcion", modificarDescripcion.text.toString())
            //paramObject.put("fecha_vencimiento", product.fecha_vencimiento)
            paramObject.put("tiempo_preparacion", product.tiempo_preparacion)
            paramObject.put("contenido", product.contenido)
           // paramObject.put("imagenes", product.imagenes)

            productosControlador.editarDatosProducto(paramObject.toString(), product.id!!)
                .enqueue(object : Callback<Void> {
                    override fun onFailure(call: Call<Void>, t: Throwable) {
                       Toast.makeText(
                            requireContext(),
                            "No se ha podido modificar el usuario",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        Toast.makeText(
                            requireContext(),
                            "Modificación exitosa!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        }
        else{Toast.makeText(requireContext(),"Debe completar los campos vacíos!",Toast.LENGTH_SHORT).show()}
    }
}
