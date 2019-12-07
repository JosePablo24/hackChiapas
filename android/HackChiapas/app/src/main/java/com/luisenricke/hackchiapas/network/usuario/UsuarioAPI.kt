package com.luisenricke.hackchiapas.network.usuario

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.luisenricke.hackchiapas.data.entity.Usuario
import com.luisenricke.hackchiapas.network.AccessToken
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface UsuarioAPI {
    data class UsuarioResponse(
        @SerializedName("user")
        @Expose
        val usuario: Usuario,
        @SerializedName("access_token")
        @Expose
        val accessToken: AccessToken
    )


    @FormUrlEncoded
    @POST("/auth/login")
    fun login(
        @Field("username") user: String,
        @Field("password") password: String
    ): Call<UsuarioResponse>
}