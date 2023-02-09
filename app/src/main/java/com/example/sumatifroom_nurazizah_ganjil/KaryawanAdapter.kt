package com.example.sumatifroom_nurazizah_ganjil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sumatifroom_nurazizah_ganjil.room.TbKaryawan
import kotlinx.android.synthetic.main.activity_karyawan_adapter.view.*

class KaryawanAdapter (private val karyawan: ArrayList<TbKaryawan>,private val listener: OnAdapterListener)
    : RecyclerView.Adapter<KaryawanAdapter.KaryawanViewHolder>() {

    class KaryawanViewHolder(val view: View):RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KaryawanViewHolder {
        return KaryawanViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_karyawan_adapter,parent,false)
        )
    }

    override fun onBindViewHolder(holder: KaryawanViewHolder, position: Int) {
        val tbKaryawan= karyawan[position]
        holder.view.T_id.text = tbKaryawan.id_karyawan.toString()
        holder.view.T_nama.text = tbKaryawan.nama
        holder.view.T_nama.setOnClickListener{
            listener.onClick(tbKaryawan)
        }

        holder.view.icon_edit.setOnClickListener{
            listener.onUpdate(tbKaryawan)
        }
        holder.view.icon_delete.setOnClickListener{
            listener.onDelete(tbKaryawan)
        }
    }

    override fun getItemCount()= karyawan.size

    fun setData(list: List<TbKaryawan>){
        karyawan.clear()
        karyawan.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener{
        fun onClick(tbKaryawan: TbKaryawan)
        fun onUpdate(tbKaryawan: TbKaryawan)
        fun onDelete(tbKaryawan: TbKaryawan)

    }



}