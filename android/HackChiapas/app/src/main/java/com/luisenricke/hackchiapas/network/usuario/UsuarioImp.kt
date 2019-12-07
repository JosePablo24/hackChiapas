package com.luisenricke.hackchiapas.network.usuario

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.luisenricke.hackchiapas.data.entity.Usuario
import com.luisenricke.hackchiapas.network.AccessToken
import com.luisenricke.hackchiapas.network.BaseWebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


object UsuarioImp : BaseWebService<UsuarioAPI>() {


    private const val ROUTE = "/auth/login/"
    override fun makeService(): UsuarioAPI {
        return setupRetrofit(
            BASE_URL + ROUTE,
            setupHttpClient()
        ).create(UsuarioAPI::class.java)
    }

    fun login(email: String, password: String): Usuario {
        var usuario: Usuario = Usuario()
        makeService().login(email, password)
            .enqueue(object : Callback<UsuarioAPI.UsuarioResponse> {
                override fun onFailure(call: Call<UsuarioAPI.UsuarioResponse>, t: Throwable) {
                    Timber.w("Respuesta no llamada")
                }

                override fun onResponse(
                    call: Call<UsuarioAPI.UsuarioResponse>,
                    response: Response<UsuarioAPI.UsuarioResponse>
                ) {
                    usuario = response.body()?.usuario!!
                    Timber.i("${usuario.nombre} ${usuario.apellido}")
                }
            })
        return usuario
    }

}