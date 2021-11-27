package com.example.app.fragments.agandaFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.model.Tarea

class AgendaAdpter: RecyclerView.Adapter<AgendaAdpter.ViewHolder>() {

    private var agendaList = emptyList<Tarea>()

    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {

        val id=view.findViewById(R.id.tvID) as TextView
        val titulo=view.findViewById(R.id.tvTitulo) as TextView
        val contenido=view.findViewById(R.id.tvContenido) as TextView
        val finaliza=view.findViewById(R.id.tvFinaliza) as TextView
        val layaout = view.findViewById(R.id.card) as View


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return ViewHolder(
            layoutInflater.inflate(R.layout.agenda_adapter_row,parent,false)
        )    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = agendaList[position]
        holder.id.text=currentItem.id.toString()
        holder.titulo.text = currentItem.nombreTarea
        holder.contenido.text = currentItem.contenidoTarea
        holder.finaliza.text=currentItem.finalizaTarea

        holder.layaout.setOnClickListener {
            val action = AgendaFragmentDirections.actionFirstFragmentToSecondFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return agendaList.size
    }

    fun setData(tarea:List<Tarea>){

        this.agendaList = tarea
        notifyDataSetChanged()
    }
}