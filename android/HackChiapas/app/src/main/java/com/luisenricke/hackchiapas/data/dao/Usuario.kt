package com.luisenricke.hackchiapas.data.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Suppress("SpellCheckingInspection")
@Entity(tableName = Usuario.SCHEMA.TABLE)
data class Usuario(
  @ColumnInfo(name = SCHEMA.CORREO)
  val correo: String,
  @ColumnInfo(name = SCHEMA.PASSWORD)
  val password: String,
  @ColumnInfo(name = SCHEMA.TELEFONO)
  val telefono: String,
  @ColumnInfo(name = SCHEMA.NOMBRE)
  val nombre: String,
  @ColumnInfo(name = SCHEMA.APELLIDO)
  val apellido: String,
  @ColumnInfo(name = SCHEMA.GENERO)
  val genero: String,
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = SCHEMA.ID)
  val id: Int = 0
) {
  object SCHEMA {
    const val TABLE = "Usuario"
    const val ID = "id"
    const val CORREO = "correo"
    const val PASSWORD = "password"
    const val TELEFONO = "telefono"
    const val NOMBRE = "nombre"
    const val APELLIDO = "apellido"
    const val GENERO = "genero"
  }
}
