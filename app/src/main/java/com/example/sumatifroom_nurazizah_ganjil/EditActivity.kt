package com.example.sumatifroom_nurazizah_ganjil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.sumatifroom_nurazizah_ganjil.room.Codepelita
import com.example.sumatifroom_nurazizah_ganjil.room.Constant
import com.example.sumatifroom_nurazizah_ganjil.room.TbKaryawan
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {
    val db by lazy { Codepelita(this) }
    private var tbKaryawanID:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setupView()
       tombolPerintah()
        tbKaryawanID= intent.getIntExtra("intent_id",tbKaryawanID)
        Toast.makeText(this,tbKaryawanID.toString(),Toast.LENGTH_SHORT).show()

    }

    fun setupView(){
        val intentType = intent.getIntExtra("intent_type",0)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        when(intentType){
            Constant.TYPE_CREATE ->{
                btnupdate.visibility = View.GONE

            }
            Constant.TYPE_READ ->{
                btnsave.visibility = View.GONE
                btnupdate.visibility = View.GONE
                ETid.visibility = View.GONE
                tampilkaryawan()
            }

            Constant.TYPE_UPDATE ->{
                btnsave.visibility = View.GONE
                ETid.visibility = View.GONE
                tampilkaryawan()
            }
        }
    }

    fun tampilkaryawan(){
        tbKaryawanID = intent.getIntExtra("intent_id",0)
        CoroutineScope(Dispatchers.IO).launch {
            val karyawan = db.karyawanDao().tampilkaryawan(tbKaryawanID)[0]
            val dataId: String = karyawan.id_karyawan.toString()
            ETid.setText(dataId)
            ETnama.setText(karyawan.nama)
            ETalamat.setText(karyawan.alamat)
            ETusia.setText(karyawan.usia)
        }
    }

    fun tombolPerintah(){
        btnsave.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.karyawanDao().addKaryawan(
                    TbKaryawan(
                        ETid.text.toString().toInt(),
                        ETnama.text.toString(),
                        ETalamat.text.toString(),
                        ETusia.text.toString()
                    )
                )
                finish()
            }
        }

        btnupdate.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.karyawanDao().updateKaryawan(
                    TbKaryawan(
                        tbKaryawanID,
                        ETnama.text.toString(),
                        ETalamat.text.toString(),
                        ETusia.text.toString()
                    )
                )
                finish()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}