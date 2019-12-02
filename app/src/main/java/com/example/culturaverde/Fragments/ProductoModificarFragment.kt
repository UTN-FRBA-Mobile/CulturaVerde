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
import android.util.Base64
import androidx.core.content.ContextCompat
import androidx.core.net.toFile
import androidx.core.view.get
import androidx.core.view.size
import com.example.culturaverde.Models.Photo
import kotlinx.android.synthetic.main.product_row_item.*
import kotlinx.android.synthetic.main.producto_modificar_row_item.*
import kotlinx.android.synthetic.main.producto_modificar_row_item.product_image
import kotlinx.android.synthetic.main.producto_modificar_row_item.view.*
import kotlinx.android.synthetic.main.productos_productor_fragment.*
import okhttp3.FormBody
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import java.io.File
import java.io.InputStream
import java.text.Normalizer

class ProductoModificarFragment : Fragment() {



    companion object {
        fun newInstance() = ProductoModificarFragment()
    }

    private lateinit var viewModel: ProductoModificarViewModel
    private lateinit var modificarProductoAdapter: ModificarProductoAdapter
    private lateinit var productosControlador: ProductosControlador
    val product: ProductoProductor=ProductoGlobal.getProducto()
    private var imagenes:ArrayList<Photo> = arrayListOf()
    var imagenes_adapter:ArrayList<Photo> = arrayListOf()
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

        imagenes_adapter.addAll(product.imagenes)

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

            var cantidad_fotos = visorFotosProducto.size

            var imagen:Photo = Photo()

            imagen.uri = image_uri

       //     imagenes_adapter.addAll(product.imagenes)

            imagenes_adapter.add(imagen)

            imagenes.add(imagen)

            modificarProductoAdapter = ModificarProductoAdapter(requireContext(), imagenes_adapter)

            visorFotosProducto.adapter = modificarProductoAdapter
            modificarProductoAdapter.notifyDataSetChanged()

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
                            "No se ha podido modificar el producto",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onResponse(call: Call<Void>, response: Response<Void>) {

                        var cantidad_fotos= visorFotosProducto.size

                        if(cantidad_fotos!=product.imagenes.size){

                            imagenes.forEach { img->

                                subirArchivos(img,product.id!!)

                            }

                        }

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


    fun subirArchivos(imagen:Photo, id:Long){

        productosControlador =
            APIConfig.getRetrofitClient(requireContext()).create(ProductosControlador::class.java)

        var cant = imagenes.size

         //   val file = uri!!.toFile()

        var uri =imagen.uri

        var path:String = uri!!.path!!

//var filename:String = path.substring(path.lastIndexOf("/")+1)

      //  var file = filename.substring(0, filename.lastIndexOf("."))

        var file = File(path)
       // val inputStream = contentResolver.openInputStream(Uri.fromFile(file))
       // val requestFile = RequestBody.create(MediaType.parse("image/jpeg"), getBytes(inputStream))
       // var body = MultipartBody.Part.createFormData("image", file.name, requestFile)

            // Create a request body with file and image media type
     var fileReqBody:RequestBody = RequestBody.create(MediaType.parse("image/*"), file)
     // Create MultipartBody.Part using file request-body,file name and part name
   //  var part:MultipartBody.Part = MultipartBody.Part.createFormData("upload", file.getName(), fileReqBody)

      //  var data: FormBody

     //  var data = FormData()
      //      data.append("file", file)
      //      data.append("name", file.getName())

        var requestBody = MultipartBody.Builder()
        .setType(MultipartBody.FORM)
            .addFormDataPart("file","file", fileReqBody)
        .addFormDataPart("name", file.getName())
        .build()

        var pp:ProductoProductor = ProductoProductor(product.id)

        productosControlador.subirFotos(requestBody,pp)
            .enqueue(object : Callback<Void> {
                override fun onFailure(call: Call<Void>, t: Throwable) {

                //    Toast.makeText(
                  //      requireContext(),
                 //       "Ocurrió un error inesperado, intente nuevamente",
                //        Toast.LENGTH_SHORT
                //    ).show()


                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {


                }
            })

    }



}

