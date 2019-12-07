package com.luisenricke.hackchiapas.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Suppress("SpellCheckingInspection")
@Entity(tableName = Usuario.SCHEMA.TABLE)
data class Usuario(
    @SerializedName("username")
    @Expose
    @ColumnInfo(name = SCHEMA.USER)
    val correo: String,
    @SerializedName("phone")
    @Expose
    @ColumnInfo(name = SCHEMA.TELEFONO)
    val telefono: String,
    @SerializedName("name")
    @Expose
    @ColumnInfo(name = SCHEMA.NOMBRE)
    val nombre: String,
    @SerializedName("lastname")
    @Expose
    @ColumnInfo(name = SCHEMA.APELLIDO)
    val apellido: String,
    @SerializedName("genre")
    @Expose
    @ColumnInfo(name = SCHEMA.GENERO)
    val genero: String,
    @SerializedName("id")
    @Expose
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SCHEMA.ID)
    val id: Int = 0
) {
    object SCHEMA {
        const val TABLE = "Usuario"
        const val ID = "id"
        const val USER = "usuario"
        const val TELEFONO = "telefono"
        const val NOMBRE = "nombre"
        const val APELLIDO = "apellido"
        const val GENERO = "genero"
    }

    constructor() : this("","","","","",-1)
}
