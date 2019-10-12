import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.example.culturaverde.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.producto_row_item.view.*

class ProductAdapter(var context: Context, var products: List<Producto> = arrayListOf()) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProductAdapter.ViewHolder {
        // The layout design used for each list item
        val view = LayoutInflater.from(context).inflate(R.layout.producto_row_item, null)
        return ViewHolder(view)

    }
    // This returns the size of the list.
    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(viewHolder: ProductAdapter.ViewHolder, position: Int) {
        //we simply call the `bindProduct` function here
        viewHolder.bindProduct(products[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // This displays the product information for each item
        fun bindProduct(producto: Producto) {

            itemView.producto_titulo.text = producto.titulo
            itemView.producto_precio.text = "$${producto.precio.toString()}"
            Picasso.get().load(producto.imagenes[0].filename).fit().into(itemView.producto_imagen)
        }

    }

}