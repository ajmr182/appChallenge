package com.example.app.fragments.descripcionFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R

class ComentariosAdapter : RecyclerView.Adapter<ComentariosAdapter.ViewHolder>() {

    private var listaComentarios = arrayListOf<String>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            layoutInflater.inflate(R.layout.comentarios_adapter_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(listaComentarios[position])
    }

    override fun getItemCount(): Int {

        return listaComentarios.size
    }

    fun setData(list: ArrayList<String>) {

        listaComentarios = list
        notifyDataSetChanged()
    }

    fun addComentario(comentario: String) {

        listaComentarios.add(comentario)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var comentarioAgregado = view.findViewById<TextView>(R.id.tvComentario)

        fun bind(comentario: String) {

            comentarioAgregado.text = comentario
        }
    }


}