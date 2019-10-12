import retrofit2.Call
import retrofit2.http.*

interface ControladorProducto {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("bestRated")
    fun getProducts(
    ): Call<List<Producto>>



}