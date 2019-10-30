package com.example.culturaverde.Adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.culturaverde.Classes.ReservaGlobal
import com.example.culturaverde.Classes.UsuarioGlobal
import com.example.culturaverde.Controllers.ReservasControlador
import com.example.culturaverde.Models.DetalleReserva
import com.example.culturaverde.Models.Reserva
import com.example.culturaverde.R
import com.example.culturaverde.Services.APIConfig
import com.example.culturaverde.Ui.Reservas.ReservasFragment
import kotlinx.android.synthetic.main.detalle_reserva_item.view.*
import kotlinx.android.synthetic.main.reservas_item.*
import kotlinx.android.synthetic.main.reservas_item.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.text.SimpleDateFormat


class ReservasAdapter(var context: Context, var reservas: List<Reserva> = arrayListOf()) :
    RecyclerView.Adapter<ReservasAdapter.ViewHolder>() {

    private val ctx:Context=context

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ReservasAdapter.ViewHolder {
        // The layout design used for each list item
        val view = LayoutInflater.from(context).inflate(R.layout.reservas_item, null)
        return ViewHolder(view)

    }
    // This returns the size of the list.
    override fun getItemCount(): Int = reservas.size

    override fun onBindViewHolder(viewHolder: ReservasAdapter.ViewHolder, position: Int) {
        //we simply call the `bindProduct` function here
        viewHolder.bindProduct(reservas[position],ctx)


    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // This displays the product information for each item

        fun bindProduct(reserva: Reserva, ctx:Context) {

            var detalleReservasAdapter: DetalleReservaAdapter

            itemView.layout_detalle_reserva.setVisibility(View.GONE)
            itemView.floatingActionButtonCalificarProductor.hide()
            itemView.floatingActionButtonMostrarDetalle.hide()
            itemView.floatingActionButtonCambioEstado.hide()

            var isOpen:Boolean=false
            var isOpenDetalle:Boolean=false

            itemView.texto_estado.text = "Estado"
            itemView.estado.text = reserva.estado_reserva!!.nombre

            itemView.datos_retiro.text = "Datos de retiro"
            itemView.texto_fecha_creacion.text = "Fecha de creación"

            val date = SimpleDateFormat("dd/MM/yyy").format(reserva.fecha_creacion)
            itemView.fecha_creacion.text = "${date}"

            itemView.persona_retiro.text = "Retira "+reserva.persona_retiro

            if(reserva.punto_entrega!=null) {

                itemView.direccion_retiro.text = "por "+reserva.punto_entrega!!.direccion+","

                itemView.localidad_retiro.text = reserva.punto_entrega!!.localidad+","
            }

            if(reserva.fecha!=null && reserva.forma_retiro!="Acuerda con Productor") {
                val date2 = SimpleDateFormat("dd/MM/yyy").format(reserva.fecha)
                itemView.fecha_retiro.text = "el "+"${date2}"

            }else{

                itemView.direccion_retiro.text = "con fecha y lugar de retiro"
                itemView.localidad_retiro.text ="a coordinar"

            }

            if(UsuarioGlobal.getUsuario().rol=="Productor") {

                itemView.usuario.text = "Consumidor"
                itemView.nombre.text = reserva.consumidor.usuario.nombre
                itemView.apellido.text=reserva.consumidor.usuario.apellido
                itemView.tel.text=reserva.consumidor.usuario.telefono

            }else{

                itemView.usuario.text = "Productor"
                itemView.nombre.text = reserva.productor.usuario.nombre
                itemView.apellido.text=reserva.productor.usuario.apellido
                itemView.tel.text=reserva.productor.usuario.telefono


            }

            itemView.total_reserva.text = "Total"
            itemView.total.text = "$${reserva.total_reserva}"

            itemView.floatingActionButtonMostrarDetalle.setOnClickListener { view ->

                if(isOpenDetalle) {
                    itemView.layout_detalle_reserva.visibility=View.GONE
                    isOpenDetalle=false
                }else{

                    itemView.layout_detalle_reserva.setVisibility(View.VISIBLE)
                    isOpenDetalle=true

                }
            }

            itemView.floatingActionButtonCalificarProductor.setOnClickListener { view ->

                view.findNavController().navigate(R.id.action_nav_reservasFragment_to_calificarProductorFragment)

            }

            itemView.floatingActionButtonCambioEstado.setOnClickListener { view ->

                if(UsuarioGlobal.getUsuario().rol=="Productor"){

                    ReservaGlobal.guardarReserva(Reserva(reserva.id,reserva.productor,reserva.consumidor,reserva.punto_entrega,
                        reserva.estado_reserva,reserva.fecha,reserva.fecha_creacion,reserva.total_reserva,reserva.forma_retiro,
                        reserva.persona_retiro,reserva.detalleReserva,reserva.calificacion))

                    view.findNavController().navigate(R.id.action_nav_reservasFragment_to_estadoReservaFragment2)

                    return@setOnClickListener

                }


                ReservaGlobal.guardarReserva(Reserva(reserva.id,reserva.productor,reserva.consumidor,reserva.punto_entrega,
                    reserva.estado_reserva,reserva.fecha,reserva.fecha_creacion,reserva.total_reserva,reserva.forma_retiro,
                    reserva.persona_retiro,reserva.detalleReserva,reserva.calificacion))

                view.findNavController().navigate(R.id.action_nav_reservasFragment_to_estadoReservaFragment)

            }

            itemView.floatingActionButtonGeneral.setOnClickListener { view ->

                if(isOpen) {

                    itemView.floatingActionButtonCalificarProductor.hide()
                    itemView.floatingActionButtonMostrarDetalle.hide()
                    itemView.floatingActionButtonCambioEstado.hide()

                    isOpen=false

                }else {

                    if(UsuarioGlobal.getUsuario().rol=="Productor"){

                        itemView.floatingActionButtonMostrarDetalle.show()

                        if(reserva.estado_reserva!!.nombre!="Finalizado" && reserva.estado_reserva!!.nombre!="Cancelado"){

                            itemView.floatingActionButtonCambioEstado.show()

                        }

                        isOpen=true

                        return@setOnClickListener

                    }

                    if(reserva.estado_reserva!!.nombre=="Finalizado" || reserva.estado_reserva!!.nombre=="Cancelado") {

                        itemView.floatingActionButtonCalificarProductor.show()
                        itemView.floatingActionButtonMostrarDetalle.show()

                        isOpen=true

                        return@setOnClickListener
                    }

                    itemView.floatingActionButtonMostrarDetalle.show()
                    itemView.floatingActionButtonCambioEstado.show()

                    isOpen=true
                }
            }

            itemView.recycler_detalle_reserva.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            detalleReservasAdapter = DetalleReservaAdapter(ctx,reserva.detalleReserva)
            itemView.recycler_detalle_reserva.adapter = detalleReservasAdapter
            detalleReservasAdapter.notifyDataSetChanged()


        }



    }


}