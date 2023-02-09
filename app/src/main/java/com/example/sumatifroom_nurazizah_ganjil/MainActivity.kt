package com.example.sumatifroom_nurazizah_ganjil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sumatifroom_nurazizah_ganjil.room.Codepelita
import com.example.sumatifroom_nurazizah_ganjil.room.Constant
import com.example.sumatifroom_nurazizah_ganjil.room.TbKaryawan
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    val db by lazy { Codepelita(this) }
    lateinit var karyawanAdapter: KaryawanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        halEdit()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }
    fun loadData(){
        CoroutineScope(Dispatchers.IO).launch {
            val karyawan = db.karyawanDao().getKaryawan()
            Log.d("MainActivity","dbResponse: $karyawan")
            withContext(Dispatchers.Main){
                karyawanAdapter.setData(karyawan)
            }
        }
    }
    private fun halEdit(){
        btnInput.setOnClickListener{
            intentEdit(0,Constant.TYPE_CREATE)
        }
    }


    fun intentEdit(tbKaryawanid: Int, intentType:Int){
        startActivity(
            Intent(applicationContext, EditActivity::class.java)
                .putExtra("intent_id",tbKaryawanid)
                .putExtra("intent_type",intentType)
        )
    }


    fun setupRecyclerView(){
        karyawanAdapter = KaryawanAdapter(arrayListOf(),object : KaryawanAdapter.OnAdapterListener{
            override fun onClick(tbKaryawan: TbKaryawan) {
                intentEdit(tbKaryawan.id_karyawan,Constant.TYPE_READ)
            }

            override fun onUpdate(tbKaryawan: TbKaryawan) {
                intentEdit(tbKaryawan.id_karyawan,Constant.TYPE_UPDATE)
            }

            override fun onDelete(tbKaryawan: TbKaryawan) {
                hapusdatakaryawan(tbKaryawan)
            }
        })
        list_datakaryawan.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = karyawanAdapter
        }
    }

    private fun hapusdatakaryawan (tbKaryawan: TbKaryawan){
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("Konfirmasi Hapus")
            setMessage("Yakin Hapus ${tbKaryawan.id_karyawan}?")
            setNegativeButton("Batal") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus") { dialogInterface, i->
                CoroutineScope(Dispatchers.IO).launch {
                    dialogInterface.dismiss()
                    loadData()
                }
            }
        }
        dialog.show()
    }
}