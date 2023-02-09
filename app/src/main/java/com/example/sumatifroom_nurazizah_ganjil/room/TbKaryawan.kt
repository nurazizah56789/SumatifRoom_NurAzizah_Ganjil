package com.example.sumatifroom_nurazizah_ganjil.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TbKaryawan(
    @PrimaryKey (autoGenerate = true)
    val id_karyawan:Int,
    val nama:String,
    val alamat:String,
    val usia:String
)
