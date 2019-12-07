package com.luisenricke.hackchiapas.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.luisenricke.hackchiapas.data.BaseDAO
import com.luisenricke.hackchiapas.data.entity.Usuario
import com.luisenricke.hackchiapas.data.entity.Usuario.SCHEMA

@Suppress("SpellCheckingInspection")
@Dao
abstract class UsuarioDAO : BaseDAO<Usuario>,
  BaseDAO.UpdateDAO<Usuario>,
  BaseDAO.DeleteDAO<Usuario>,
  BaseDAO.OperationsPrimaryKeyDAO<Usuario> {

  @Query("SELECT COUNT(*) FROM ${SCHEMA.TABLE}")
  abstract override fun count(): Int

  @Query("SELECT * FROM ${SCHEMA.TABLE}")
  abstract override fun get(): List<Usuario>

  @Query("DELETE FROM ${SCHEMA.TABLE}")
  abstract override fun drop()

  @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id = :id")
  abstract override fun get(id: Int): Usuario

  @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id IN(:ids)")
  abstract override fun get(ids: LongArray): List<Usuario>

  @Query("DELETE FROM ${SCHEMA.TABLE} WHERE id = :id")
  abstract override fun delete(id: Int): Int
}
