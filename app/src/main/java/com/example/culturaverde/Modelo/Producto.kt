import com.google.gson.annotations.SerializedName

data class Producto(
    @SerializedName("descripcion")
    var descripcion: String,

    @SerializedName("id")
    var id: Int,

    @SerializedName("titulo")
    var titulo: String,
    @SerializedName("precio")
    var precio: String,

    @SerializedName("imagenes")
    var imagenes: List<Imagen> = arrayListOf()
)