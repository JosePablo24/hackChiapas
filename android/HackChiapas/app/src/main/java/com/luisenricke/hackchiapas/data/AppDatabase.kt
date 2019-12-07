package com.luisenricke.hackchiapas.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.luisenricke.hackchiapas.data.dao.Usuario
import com.luisenricke.hackchiapas.data.entity.UsuarioDAO
import timber.log.Timber

@Database(
  entities = [
    Usuario::class
  ],
  version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

  abstract fun usuario(): UsuarioDAO

  companion object {
    private const val NAME = "Database.db"

    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
      return INSTANCE ?: synchronized(this) {
        INSTANCE ?: buildDatabase(context)
          .also { INSTANCE = it }
      }
    }

    fun close() {
      if (INSTANCE?.isOpen == true) {
        INSTANCE?.close()
      }
      INSTANCE = null
    }

    private fun buildDatabase(context: Context): AppDatabase {
      return Room.databaseBuilder(context, AppDatabase::class.java, NAME)
        .addCallback(object : Callback() {
          override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            ioThread {

            }
          }

          override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            Timber.v("Database open")
          }
        })
        .allowMainThreadQueries()
        .build()
    }
  }
}
