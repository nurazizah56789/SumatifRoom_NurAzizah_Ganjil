package com.example.sumatifroom_nurazizah_ganjil.room

import androidx.room.*

@Dao
interface KaryawanDao {

    @Insert
    fun addKaryawan(tbKaryawan: TbKaryawan)

    @Update
    fun updateKaryawan(tbKaryawan: TbKaryawan)

    @Delete
    fun deleteKaryawan(tbKaryawan: TbKaryawan)

    @Query("SELECT * FROM tbkaryawan")
    fun getKaryawan():List<TbKaryawan>

    @Query("SELECT * FROM TbKaryawan Where id_karyawan =:tbkaryawan_id")
    fun tampilkaryawan(tbkaryawan_id: Int):List<TbKaryawan>

}