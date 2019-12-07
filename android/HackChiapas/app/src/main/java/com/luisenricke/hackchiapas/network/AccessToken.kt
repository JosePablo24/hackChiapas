package com.luisenricke.hackchiapas.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Suppress("SpellCheckingInspection")
data class AccessToken(
    @SerializedName("type")
    @Expose
    val type: String,
    @SerializedName("token")
    @Expose
    val token: String,
    @SerializedName("refreshToken")
    @Expose
    val refreshToken: String
)