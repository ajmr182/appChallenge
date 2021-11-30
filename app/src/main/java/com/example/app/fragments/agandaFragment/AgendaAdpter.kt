package com.example.app.fragments.agandaFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.model.Tarea

class AgendaAdpter(val listener: (Tarea) -> Unit) :
    RecyclerView.Adapter<AgendaAdpter.ViewHolder>() {

    private var agendaList = emptyList<Tarea>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            layoutInflater.inflate(R.layout.agenda_adapter_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(agendaList[position])
    }

    override fun getItemCount(): Int {

        return agendaList.size
    }

    fun setData(tareas: List<Tarea>) {

        agendaList = tareas
        notifyDataSetChanged()
    }

    fun getFilteredData(tareas: List<Tarea>) {

        agendaList = tareas
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val id = view.findViewById<TextView>(R.id.tvID)
        val titulo = view.findViewById<TextView>(R.id.tvTitulo)
        val contenido = view.findViewById<TextView>(R.id.tvContenido)
        val finaliza = view.findViewById<TextView>(R.id.tvFinaliza)
        val inicio = view.findViewById<TextView>(R.id.tvFechaActual)
        val tareaLista = view.findViewById<CheckBox>(R.id.checkBoxTareaFinalizada)
        val layaout = view.findViewById<View>(R.id.card)

        fun bind(tarea: Tarea) {

            id.text = tarea.id.toString()
            titulo.text = tarea.nombreTarea
            contenido.text = tarea.contenidoTarea
            finaliza.text = tarea.fechaFinal
            inicio.text = tarea.fechaInicio
            tareaLista.isChecked = tarea.tareaEstaLista

            layaout.setOnClickListener {

                val action = AgendaFragmentDirections.actionFirstFragmentToSecondFragment(tarea)
                itemView.findNavController().navigate(action)
            }

            tareaLista.setOnClickListener {

                if (tareaLista.isChecked) {

                    tarea.tareaEstaLista = true
                    listener(tarea)

                } else {

                    tarea.tareaEstaLista = false
                    listener(tarea)
                }
            }
        }
    }
}
