package com.example.sumatifroom_nurazizah_ganjil.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.concurrent.locks.Lock

@Database(entities = [TbKaryawan::class], version = 1)
abstract class Codepelita : RoomDatabase() {
    abstract fun karyawanDao():KaryawanDao

    companion object{
        @Volatile private var instance:Codepelita? = null
        private val Lock = Any()
        operator fun invoke(context: Context) = instance?: synchronized(Lock){
            instance?: buildDatabase(context).also{
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            Codepelita::class.java,
            "205442.db"
        ) .build()

    }
}