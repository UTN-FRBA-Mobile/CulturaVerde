package com.example.culturaverde.Services

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.culturaverde.Classes.UsuarioGlobal
import com.example.culturaverde.Controllers.UsuarioControlador
import com.example.culturaverde.Models.Usuario
import com.example.culturaverde.R
import com.google.firebase.iid.FirebaseInstanceIdService
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.fragment_crearusuarioconsumidor.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class MyFirebaseInstanceService : FirebaseInstanceIdService() {
    private val TAG = "MyFirebaseInstanceServi"


    override fun onTokenRefresh() {
        // Get updated InstanceID token.
        val refreshedToken = FirebaseInstanceId.getInstance().token
        val sharedPref = getSharedPreferences(
            getString(R.string.user_preference_key), Context.MODE_PRIVATE
        )
        val sharedPrefEditor = sharedPref.edit()
        sharedPrefEditor.putString("deviceToken", refreshedToken)
        sharedPrefEditor.apply()
        // here we send the new token to our backend

        sendRegistrationToServer(refreshedToken)

    }

    companion object {
        fun sendRegistrationToServer(refreshedToken: String?) {
            val usuarioControlador =
                APIConfig.getRetrofitClient().create(UsuarioControlador::class.java)

            var usuario: Usuario?

            if (UsuarioGlobal.getUsuario() != null) {
                usuario = UsuarioGlobal.getUsuario()

                val paramObject = JSONObject()

                paramObject.put("deviceToken", refreshedToken)
                paramObject.put("id", usuario!!.id)

                usuarioControlador.registrarToken(paramObject.toString())
                    .enqueue(object : Callback<Void> {
                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            print(t.message)
                            Log.d("envio de token erroneo", t.message)
                        }

                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            Log.d("envio de token exitoso", response.message())
                        }
                    })
                // send registration token To server
            }
        }
    }

}